package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.MRoom;
import com.example.demo.entity.MUser;
import com.example.demo.entity.TMessages;
import com.example.demo.entity.TRoomUser;
import com.example.demo.form.MessageForm;
import com.example.demo.form.RoomForm;
import com.example.demo.service.RoomService;
import com.example.demo.service.RoomUserService;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserDetailServiceImpll;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class RoomController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoomService roomService;

	@Autowired
	private RoomUserService roomUserService;

	@GetMapping("/")
	public String getRoomsIndex(Model model, @AuthenticationPrincipal UserDetailServiceImpll loginUser) {

		//ログインユーザーのユーザー名取得
		MUser user = loginUser.getUser();
		model.addAttribute("user", user);
		
		List<MRoom> rooms = roomService.getLoginUserRooms(loginUser);
		model.addAttribute("rooms", rooms);

		return "rooms/index";
	}

	@GetMapping("/rooms/new")
	public String getRoomsNew(Model model, @ModelAttribute("form") RoomForm form,
			@AuthenticationPrincipal UserDetailServiceImpll loginUser) {

		//ログインユーザーのユーザーID取得
		int currentUserId = loginUser.getUser().getId();

		//ユーザー取得(複数件)
		List<MUser> users = userService.getUsers(currentUserId);
		model.addAttribute("users", users);

		return "rooms/new";
	}

	@PostMapping("/rooms/new")
	public String postRoomsNew(Model model, @Validated @ModelAttribute("form") RoomForm form, BindingResult result,
			TRoomUser roomUser, @AuthenticationPrincipal UserDetailServiceImpll loginUser) {

		//入力チェック
		if (result.hasErrors()) {
			/* NG:チャットルーム作成画面に戻る*/
			return "redirect:/rooms/new";
		}

		log.info(form.toString());

		//チャットルーム・roomUserTBL登録
		roomUserService.registRoomUser(form, roomUser, loginUser);

		return "redirect:/";
	}

	@GetMapping("/rooms/{roomId}")
	public String getRoom(Model model, @AuthenticationPrincipal UserDetailServiceImpll loginUser,
			@PathVariable("roomId") int id, @ModelAttribute("form") MessageForm form) {

		//ログインユーザーの情報を取得
		MUser user = loginUser.getUser();
		model.addAttribute("user", user);
		String username = loginUser.getUser().getName();
		int loginUserId = loginUser.getUser().getId();

		model.addAttribute("username", username);

		//ログインユーザーと選択されたユーザーが保有するチャットルームを取得
		List<MRoom> rooms = roomService.getLoginUserRooms(loginUser);
		model.addAttribute("rooms", rooms);

		//room_usersテーブルのレコード(1件)取得
		TRoomUser roomUser = roomUserService.getRoomUserOne(id);

		//room_usersに登録されているログインユーザーのIDを取得
		int currentUserId = roomUser.getCurrentUserId();
		//room_usersに登録されているチャットするユーザーのIDを取得
		int userId = roomUser.getUserId();

		//ログインユーザーとroom_usersのログインユーザーID、またはログインユーザーとチャット選択されたユーザーのIDが等しい時メッセー送信画面に遷移する
		if (loginUserId == currentUserId || loginUserId == userId) {
			//チャットルーム1件取得
			MRoom room = roomService.getRoomOne(id);
			String roomName = room.getRoomName();
			model.addAttribute("roomName", roomName);

			//チャットルームに紐づくメッセージ取得
			List<TMessages> messages = roomService.getMessagesAll(id);
			model.addAttribute("messages", messages);

			return "messages/index";
		}

		return "redirect:/";
	}

	@PostMapping(value = "/rooms/{roomId}/delete", params = "delete")
	public String deleteRoom(Model model, @AuthenticationPrincipal UserDetailServiceImpll loginUser,
			@PathVariable("roomId") int id) {
		//ログインユーザーの情報を取得
		int loginUserId = loginUser.getUser().getId();

		//room_usersテーブルのレコード(1件)取得
		TRoomUser roomUser = roomUserService.getRoomUserOne(id);

		//room_usersに登録されているログインユーザーのIDを取得
		int currentUserId = roomUser.getCurrentUserId();
		//room_usersに登録されているチャットするユーザーのIDを取得
		int userId = roomUser.getUserId();

		//ログインユーザーとroom_usersのログインユーザーID、またはログインユーザーとチャット選択されたユーザーのIDが等しい時メッセー送信画面に遷移する
		if (loginUserId == currentUserId || loginUserId == userId) {
			roomService.deleteRoomMessageOne(id);
		}

		return "redirect:/";
	}
}
