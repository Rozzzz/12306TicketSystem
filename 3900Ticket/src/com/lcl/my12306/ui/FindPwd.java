package com.lcl.my12306.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.lcl.my12306.dao.CustomerInfoDBH;
import com.lcl.my12306.utils.EmailUtils;
import com.lcl.my12306.utils.MD5AndKillAction;

import java.util.Map;

import javax.mail.MessagingException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class FindPwd {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			FindPwd window = new FindPwd();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		shell.setImage(SWTResourceManager.getImage(FindPwd.class, "/image/12306_logo.jpg"));
		shell.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_FOREGROUND));
		shell.setSize(485, 358);
		shell.setText("找回密码");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		lblNewLabel.setBounds(42, 39, 68, 26);
		lblNewLabel.setText("用户名：");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		lblNewLabel_1.setBounds(30, 93, 80, 26);
		lblNewLabel_1.setText("密保问题：");
		
		text = new Text(shell, SWT.BORDER);
		text.setMessage("请输入您的12306账号");
		text.setBounds(128, 41, 166, 26);
		
		text_1 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_1.setBounds(128, 93, 166, 26);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		
		btnNewButton.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		btnNewButton.setBounds(182, 283, 80, 27);
		btnNewButton.setText("确认");
		
		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label.setBounds(30, 145, 80, 26);
		label.setText("密保答案：");
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setMessage("请输入您的密保答案");
		
		text_2.setBounds(128, 145, 166, 26);
		
		final Label label_1 = new Label(shell, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_1.setBounds(319, 148, 126, 26);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_2.setBounds(17, 188, 93, 17);
		label_2.setText("新登录密码：");
		
		text_3 = new Text(shell, SWT.BORDER);
		
		text_3.setMessage("6~20位数字、字母");
		text_3.setBounds(128, 188, 166, 26);
		
		final Label label_3 = new Label(shell, SWT.NONE);
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_3.setBounds(308, 185, 137, 26);
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_4.setBounds(30, 231, 80, 17);
		label_4.setText("确认密码：");
		
		text_4 = new Text(shell, SWT.BORDER);
		
		text_4.setMessage("请再次输入密码");
		text_4.setBounds(128, 231, 166, 26);
		
		final Label label_5 = new Label(shell, SWT.NONE);
		label_5.setBounds(319, 231, 126, 26);
		
		final CustomerInfoDBH customerInfoDBH=new CustomerInfoDBH();
		final String accountId=text.getText().trim();
		final Map<String,String> map=customerInfoDBH.find(accountId);
		text_1.setText(map.get("question"));
		
		//密保答案
		text_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				String answer=text_2.getText().trim();
				if(answer==null || "".equals(answer)){
					label_1.setText("请输入密保答案");
				}else if(answer.equals(map.get(answer))){
					label_1.setText("");
				}else{
					label_1.setText("请输入正确的答案");
				}
				
			}
		});
		//新登录密码
		text_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String newPwd=text_3.getText().trim();
				if(newPwd==null ||"".equals(newPwd)){
					label_3.setText("请输入新的密码");
				}else{
					label_3.setText("");
				}
			}
		});
		//再次输入密码
		text_4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String newPwd=text_3.getText().trim();
				String newPwd1=text_4.getText().trim();
				if(newPwd1==null || "".equals(newPwd1)){
					label_5.setText("请再次输入密码");
				}else if(newPwd1.equals(newPwd)){
					label_5.setText("");
				}else{
					label_5.setText("请输入相同的密码");
				}
			}
		});
		//确认
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MD5AndKillAction md=new MD5AndKillAction();				
				String pwd=md.KL(md.MD5(text_3.getText().trim()));
				if(customerInfoDBH.alter_epwd(accountId, pwd)>0){
					MessageDialog.openInformation(shell, "成功提示", "修改密码成功");
				}else{
					MessageDialog.openError(shell, "失败提示", "修改密码失败");
				}
			}
		});

	}
}
