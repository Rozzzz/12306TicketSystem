package com.lcl.my12306.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.lcl.my12306.dao.CustomerInfoDBH;
import com.lcl.my12306.dao.DBHelper;
import com.lcl.my12306.utils.MD5AndKillAction;
import com.lcl.my12306.utils.UiCollections;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class UpdatePwd {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UpdatePwd window = new UpdatePwd();
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
		shell.setImage(SWTResourceManager.getImage(UpdatePwd.class, "/image/12306_logo.jpg"));
		shell.setBackgroundImage(SWTResourceManager.getImage(UpdatePwd.class, "/image/back4.jpg"));
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setSize(533, 299);
		shell.setText("修改登录密码	当前用户"+UiCollections.currentLoginUser.get("cname"));
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setOrientation(SWT.RIGHT_TO_LEFT);
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		lblNewLabel.setBounds(95, 78, 70, 23);
		lblNewLabel.setText("新密码");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setOrientation(SWT.RIGHT_TO_LEFT);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		lblNewLabel_1.setBounds(95, 119, 70, 25);
		lblNewLabel_1.setText("确认密码");
		
		text = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text.setMessage("6-20位数字、字母");
		
		text.setBounds(194, 78, 141, 23);
		
		text_1 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_1.setMessage("请再次输入密码");
		
		text_1.setBounds(194, 119, 141, 23);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
	
		btnNewButton.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		btnNewButton.setBounds(223, 190, 112, 30);
		btnNewButton.setText("确认");
		
		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label.setBounds(117, 41, 61, 17);
		label.setText("旧密码");
		
		text_2 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_2.setMessage("请输入旧密码");
		
		text_2.setBounds(194, 41, 141, 23);
				
		final Label label_1 = new Label(shell, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_1.setBounds(351, 81, 125, 17);
		
		final Label label_2 = new Label(shell, SWT.NONE);
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_2.setBounds(363, 122, 125, 17);
		
		final Label label_3 = new Label(shell, SWT.NONE);
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_3.setBounds(363, 41, 125, 17);
		
		
		
		final CustomerInfoDBH customersInfoDBH=new CustomerInfoDBH();
		final String accountId=UiCollections.currentLoginUser.get("accountid");
		final Map<String,String> map=customersInfoDBH.find(accountId);
		
		//旧密码
		text_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				MD5AndKillAction md=new MD5AndKillAction();
				String pwd=md.KL(md.MD5(text_2.getText().trim()));
				if(pwd==null || "".equals(pwd)){
					label_3.setText("请输入旧密码");
				}else if(pwd.equals(map.get("e_pwd"))){
					label_3.setText("");
				}else{
					label_3.setText("密码错误，请重新输入");
				}
			}
		});
		//输入新登录密码
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String newPwd=text.getText().trim();

				if(newPwd==null ||"".equals(newPwd)){
					label_1.setText("请输入新的登录密码");
				}else{
					label_1.setText("");
				}
			}
		});
		
		//再次输入登录密码
		text_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String newPwd=text.getText().trim();
				String newPwd1=text_1.getText().trim();
				if(newPwd1==null ||"".equals(newPwd1)){
					label_2.setText("请再次输入新的登录密码");
				}else if(newPwd1.equals(newPwd)){
					label_2.setText("");
				}else{
					label_2.setText("请输入相同的登录密码");
				}
			}
		});
		
		
		//点击确认
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MD5AndKillAction md=new MD5AndKillAction();
				String newPwd=md.KL(md.MD5(text.getText().trim()));
				
				if(customersInfoDBH.alter_epwd(accountId, newPwd)>0){
					MessageDialog.openInformation(shell, "成功提示", "修改密码成功");
					My12306 my12306=new My12306();
					shell.dispose();
					my12306.open();
					UserInfo.shell.dispose();
					UiCollections.currentLoginUser=null;
					UiCollections.userLogin.setVisible(true);
					MessageDialog.openInformation(shell, "温馨提示", "请重新登录");
					UiCollections.stackLayout.topControl=UiCollections.userLogin;
				}else{
					MessageDialog.openError(shell, "失败提示", "修改密码失败");
				}				
			}
		});

	}
}
