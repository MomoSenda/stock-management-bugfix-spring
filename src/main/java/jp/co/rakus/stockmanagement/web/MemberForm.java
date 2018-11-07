package jp.co.rakus.stockmanagement.web;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * メンバー関連のリクエストパラメータが入るフォーム.
 * @author igamasayuki
 *
 */
public class MemberForm {
	/** 名前 */
	@NotBlank(message="名前は必ず入力してください")
	private String name;
	/** メールアドレス */
	@NotBlank(message="メールアドレス必ず入力してください")
	@Email(message="e-mailの値が不正です")
	private String mailAddress;
	/** パスワード */
	@NotBlank(message="パスワードは必ず入力してください")
	@Size(min=1,max=16,message="1文字以上16文字以下で入力してください")
	private String password;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
