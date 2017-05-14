package com.lcl.my12306.ui;

import org.eclipse.swt.widgets.Composite;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import com.lcl.my12306.dao.CustomerInfoDBH;
import com.lcl.my12306.utils.EmailUtils;
import com.lcl.my12306.utils.MD5AndKillAction;
import com.lcl.my12306.utils.UiCollections;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.nebula.widgets.calendarcombo.CalendarCombo;

public class Register extends Composite {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_6;
	private Pattern p;//正则表达式
	private Matcher str;//操作的字符串
	private boolean b=false;
	static String accountId;//12306账号
	private Text text_5;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Register(final Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackgroundImage(SWTResourceManager.getImage(Register.class, "/image/back4.jpg"));
		
		Label label = new Label(composite, SWT.NONE);
		label.setText("用户注册");
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label.setFont(SWTResourceManager.getFont("华文行楷", 30, SWT.BOLD));
		label.setBounds(208, 30, 170, 42);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setText("用 户 名：");
		label_1.setBounds(105, 112, 61, 17);
		
		text = new Text(composite, SWT.BORDER);
		
		text.setMessage("由\"数字\"、\"字母\"、\"_\"组成，字母开头");
		text.setToolTipText("");
		text.setBounds(171, 109, 212, 23);
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setText("登录密码：");
		label_2.setBounds(102, 145, 61, 17);
		
		text_1 = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		
		text_1.setMessage("6~20位字母、数字");
		text_1.setBounds(171, 142, 212, 23);
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setText("确认密码：");
		label_3.setBounds(102, 178, 61, 17);
		
		text_2 = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		
		text_2.setMessage("请再次输入您的登录密码");
		text_2.setText("");
		text_2.setBounds(171, 175, 212, 23);
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setText("邮箱账号：");
		label_4.setBounds(102, 219, 61, 17);
		
		text_3 = new Text(composite, SWT.BORDER);
		
		text_3.setMessage("请正确输入邮箱账号");
		text_3.setBounds(171, 216, 212, 23);
		
		Label label_5 = new Label(composite, SWT.NONE);
		label_5.setText("验 证 码：");
		label_5.setBounds(105, 252, 61, 17);
		
		text_4 = new Text(composite, SWT.BORDER);
		
		text_4.setMessage("请输入获取的验证码");
		text_4.setBounds(171, 249, 212, 23);
		
		Button button = new Button(composite, SWT.NONE);
		
		button.setText("获取验证码");
		button.setBounds(408, 247, 80, 27);
		
		Button button_1 = new Button(composite, SWT.NONE);
		
		button_1.setText("同意协议并注册");
		button_1.setBounds(197, 361, 156, 36);
		
		
		final Label label_7 = new Label(composite, SWT.NONE);
		label_7.setText("*");
		label_7.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_7.setBounds(398, 112, 227, 17);
		
		final Label label_8 = new Label(composite, SWT.NONE);
		label_8.setText("*");
		label_8.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_8.setBounds(398, 145, 182, 17);
		
		final Label label_9 = new Label(composite, SWT.NONE);
		label_9.setText("*");
		label_9.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_9.setBounds(398, 178, 182, 17);
		
		final Label label_11 = new Label(composite, SWT.NONE);
		label_11.setText("*");
		label_11.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_11.setBounds(398, 219, 182, 17);
		
		Label label_12 = new Label(composite, SWT.NONE);
		label_12.setText("*");
		label_12.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_12.setBounds(398, 252, 5, 17);
		
		Label label_13 = new Label(composite, SWT.NONE);
		label_13.setBounds(102, 285, 61, 17);
		label_13.setText("密保问题：");
		
		final Label label_14 = new Label(composite, SWT.NONE);
		label_14.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_14.setBounds(398, 285, 182, 17);
		label_14.setText("*");
		
		Label label_15 = new Label(composite, SWT.NONE);
		label_15.setBounds(105, 318, 61, 17);
		label_15.setText("  答   案：");
		
		text_6 = new Text(composite, SWT.BORDER);
		
		text_6.setMessage("请输入密保问题答案");
		text_6.setBounds(171, 315, 212, 23);
		
		final Label label_16 = new Label(composite, SWT.NONE);
		label_16.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_16.setBounds(398, 318, 182, 17);
		label_16.setText("*");
		
		text_5 = new Text(composite, SWT.BORDER);
		text_5.setMessage("请输入密保问题");
		text_5.setBounds(171, 282, 212, 23);
		
		final Label label_6 = new Label(composite, SWT.NONE);
		label_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_6.setBounds(494, 252, 113, 17);
		
		//输入用户名(账号)时
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String account=text.getText().trim();
				p=Pattern.compile("^[A-Za-z][A-Za-z0-9_-]+$");
				str=p.matcher(account);
				b=str.matches();
				if(account==null || "".equals(account)){
					label_7 .setText("请输入用户名");
				}else if(b){
					label_7 .setText("√");
				}else{
					label_7 .setText("请按格式正确输入用户名");
				}
			}
		});
		
		//输入登录密码时
		text_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String pwd=text_1.getText().trim();
				p=Pattern.compile("^[0-9A-Za-z]{6,20}$");
				str=p.matcher(pwd);
				b=str.matches();
				if(pwd==null || "".equals(pwd)){
					label_8 .setText("请输入密码");
				}else if(b){
					label_8 .setText("√");
				}else{
					label_8.setText("请输入6~20位的数字、字母");
				}
			}
		});
		
		//再次输入登录密码
		text_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String pwd=text_1.getText().trim();
				String pwd1=text_2.getText().trim();
				if(pwd1==null || "".equals(pwd1)){
					label_9 .setText("请再次输入密码");
				}else if(pwd1.equals(pwd)){
					label_9 .setText("√");
				}else{
					label_9 .setText("请输入相同的密码");
				}
			}
		});
		
		//邮箱账号
		text_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String mailbox=text_3.getText().trim();
				p=Pattern.compile("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$");
				//p=Pattern.compile("^[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-Z0-9]-*){1,}.{1,3}[a-zA-Z-]{1,}$)");
				str=p.matcher(mailbox);
				b=str.matches();
				if(mailbox==null || "".equals(mailbox)){
					label_11 .setText("请输入邮箱账号");
				}else if(b){
					label_11 .setText("√");
				}else{
					label_11 .setText("账号格式不正确，请重新输入");
				}	
			}
		});
		//输入验证码
		text_4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String randomCode=text_4.getText().trim();
				if(randomCode==null || "".equals(randomCode)){
					label_6.setText("请输入验证码");
				}else if(randomCode.equalsIgnoreCase(EmailUtils.str)){
					label_6.setText("");
					
				}else{
					label_6.setText("");
				}
			}
		});
		
		//密保问题
		text_5.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String answer=text_5.getText().trim();
				if(answer==null || "".equals(answer)){
					label_14 .setText("请输入密保问题");
				}else{
					label_14 .setText("√");
				}
			}
		});
		
		
		//密保问题答案
		text_6.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String answer=text_6.getText().trim();
				if(answer==null || "".equals(answer)){
					label_16 .setText("请输入密保问题答案");
				}else{
					label_16 .setText("√");
				}
			}
		});
		
		
		//获取验证码
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				EmailUtils emailUtils=new EmailUtils();
				try {
					emailUtils.setMail(text_3.getText().trim());
				} catch (MessagingException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		//点击注册
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MD5AndKillAction md=new MD5AndKillAction();
				accountId=text.getText().trim();
				String e_pwd=md.KL(md.MD5(text_1.getText().trim()));
				String mailbox=text_3.getText().trim();
				String question=text_5.getText().trim();
				String answer=text_6.getText().trim();
				
								
				CustomerInfoDBH customerInfoDBH=new CustomerInfoDBH();
				if(customerInfoDBH.add1(accountId, e_pwd, mailbox, question, answer)>0){
					MessageDialog.openInformation(parent.getShell(), "成功提示", "恭喜您注册成功！");
					CompleteInfo completeInfo=new CompleteInfo();
					completeInfo.open();
					UiCollections.homePage.setVisible(true);
					UiCollections.register.setVisible(false);
				}else{
					MessageDialog.openError(parent.getShell(), "失败提示", "对不起，您注册失败！");
				}	
			}
		});	
		
//		
//		//显示密保问题
//		CustomerInfoDBH customerInfoDBH=new CustomerInfoDBH();
//		List<Map<String,String>> list=customerInfoDBH.findQuestion();
//		if(list!=null && list.size()>0){
//			for(Map<String,String> map:list){
//				text_5.add(map.get("question"));
//			}
//			text_5.select(0);
//		}
	}
	
	
	

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
