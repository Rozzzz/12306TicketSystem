package com.lcl.my12306.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

import com.lcl.my12306.dao.CustomerInfoDBH;
import com.lcl.my12306.utils.MD5AndKillAction;
import com.lcl.my12306.utils.UiCollections;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.nebula.widgets.calendarcombo.CalendarCombo;

public class UpdatePayPwd {

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
			UpdatePayPwd window = new UpdatePayPwd();
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
		shell.setImage(SWTResourceManager.getImage(UpdatePayPwd.class, "/image/12306_logo.jpg"));
		shell.setBackgroundImage(SWTResourceManager.getImage(UpdatePayPwd.class, "/image/back4.jpg"));
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		shell.setSize(580, 323);
		shell.setText("修改支付密码	当前用户"+UiCollections.currentLoginUser.get("cname"));
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setOrientation(SWT.RIGHT_TO_LEFT);
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		lblNewLabel.setBounds(86, 61, 88, 24);
		lblNewLabel.setText("旧支付密码");
		
		text = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text.setMessage("请输入旧密码");
		
		text.setBounds(205, 61, 115, 23);
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		lblNewLabel_1.setOrientation(SWT.RIGHT_TO_LEFT);
		lblNewLabel_1.setBounds(94, 107, 80, 23);
		lblNewLabel_1.setText("新支付密码");
		
		text_1 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_1.setMessage("6位数字");
		
		text_1.setBounds(205, 108, 115, 23);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		btnNewButton.setBounds(216, 227, 104, 30);
		btnNewButton.setText("确认");
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		lblNewLabel_2.setBounds(78, 160, 96, 24);
		lblNewLabel_2.setText("再次输入密码");
		
		text_2 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_2.setMessage("请再次输入密码");
		
		text_2.setBounds(205, 157, 115, 23);
		
		final Label label = new Label(shell, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label.setBounds(350, 113, 201, 17);
		
		final Label label_1 = new Label(shell, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_1.setBounds(350, 163, 201, 17);
		
		final Label label_2 = new Label(shell, SWT.NONE);
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_2.setBounds(350, 64, 201, 17);
		
		final CustomerInfoDBH customersInfoDBH=new CustomerInfoDBH();
		final String accountId=UiCollections.currentLoginUser.get("accountid");
		final Map<String,String> map=customersInfoDBH.find(accountId);
		
		//旧支付密码
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				MD5AndKillAction md=new MD5AndKillAction();
				String oldPwd=md.KL(md.MD5(text.getText().trim()));
				if(oldPwd==null || "".equals(oldPwd)){
					label_2.setText("请输入支付密码");
				}else if(oldPwd.equals(map.get("p_pwd"))){
					label_2.setText("");
				}else{
					label_2.setText("支付密码不正确，请重新输入");
				}
			}
		});
		
		
		
		//新支付密码
		text_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String newPwd=text_1.getText().trim();
				if(newPwd==null || "".equals(newPwd)){
					label.setText("请输入新的支付密码");
				}else{
					label.setText("");
				}
			}
		});
		
		//再次输入支付密码
		text_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String newPwd=text_1.getText().trim();
				String newPwd1=text_2.getText().trim();
				if(newPwd1==null || "".equals(newPwd1)){
					label_1.setText("请再次输入新的支付密码");
				}else if(newPwd1.equals(newPwd)){
					label_1.setText("");
				}else{
					label_1.setText("请输入相同的登录密码");
				}
			}
		});
		
		
		//点击确认
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MD5AndKillAction md=new MD5AndKillAction();
				String newPwd=md.KL(md.MD5(text_1.getText().trim()));
			
				if(customersInfoDBH.alter_ppwd(accountId, newPwd)>0){
					MessageDialog.openInformation(shell, "成功提示", "修改密码成功");
					shell.close();
				}else{
					MessageDialog.openError(shell, "失败提示", "修改密码失败");
				}				
			}
		});

	}
}
