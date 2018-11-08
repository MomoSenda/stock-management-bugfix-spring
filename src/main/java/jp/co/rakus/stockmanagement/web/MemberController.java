package jp.co.rakus.stockmanagement.web;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.stockmanagement.domain.Member;
import jp.co.rakus.stockmanagement.service.MemberService;

/**
 * メンバー関連処理を行うコントローラー.
 * 
 * @author igamasayuki
 *
 */
@Controller
@RequestMapping("/member")
@Transactional
public class MemberController {

	@Autowired
	private MemberService memberService;

	// @Autowired
	// private HttpSession session;

	/**
	 * フォームを初期化します.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public MemberForm setUpForm() {
		return new MemberForm();
	}

	/**
	 * メンバー情報登録画面を表示します.
	 * 
	 * @return メンバー情報登録画面
	 */
	@RequestMapping(value = "form")
	public String form() {
		return "/member/form";
	}

	/**
	 * メンバー情報を登録します.
	 * 
	 * @param form
	 *            フォーム
	 * @param result
	 *            リザルト
	 * @param model
	 *            モデル
	 * @return ログイン画面
	 */
	@RequestMapping(value = "create")
	public String create(@Validated MemberForm form, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return form();
		}

		String mailAddress = form.getMailAddress();
		Member memberAnswer = memberService.findByMailAddress(mailAddress);

		if (memberAnswer != null) {
			// エラーを出す

			String message = "このメールアドレスは既に登録されています";
			result.rejectValue("mailAddress", null, message);
			// String message="error";
			// session.setAttribute("message",message);

			return form();
		}
		Member member = new Member();
		BeanUtils.copyProperties(form, member);
		memberService.save(member);

		// パスワードの確認
		String password = form.getPassword();
		String password2 = form.getPassword2();
		if (password2 != password) {
			String message = "パスワードと入力内容が異なります";
			result.rejectValue("password2", null, message);
			return form();
		}

		return "redirect:/";

	}

}
