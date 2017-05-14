package com.lcl.my12306.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

import com.lcl.my12306.dao.CustomerInfoDBH;
import com.lcl.my12306.utils.UiCollections;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class UpdateEmail {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Pattern p;//正则表达式
	private Matcher str;//操作的字符串
	private boolean b=false;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UpdateEmail window = new UpdateEmail();
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
		shell.setImage(SWTResourceManager.getImage(UpdateEmail.class, "/image/12306_logo.jpg"));
		shell.setBackgroundImage(SWTResourceManager.getImage(UpdateEmail.class, "/image/back4.jpg"));
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setSize(548, 294);
		shell.setText("更改邮箱	当前用户"+UiCollections.currentLoginUser.get("cname"));
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		lblNewLabel.setOrientation(SWT.RIGHT_TO_LEFT);
		lblNewLabel.setBounds(46, 120, 80, 25);
		lblNewLabel.setText("新邮箱账号");
		
		text = new Text(shell, SWT.BORDER);
		
		text.setBounds(150, 120, 134, 23);
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		lblNewLabel_1.setOrientation(SWT.RIGHT_TO_LEFT);
		lblNewLabel_1.setBounds(65, 28, 61, 17);
		lblNewLabel_1.setText("密保问题");
		
		text_1 = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		text_1.setBounds(150, 28, 134, 23);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		
	
		btnNewButton.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		btnNewButton.setBounds(188, 184, 80, 27);
		btnNewButton.setText("确认");
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		lblNewLabel_2.setBounds(76, 75, 49, 17);
		lblNewLabel_2.setText("答     案");
		
		text_2 = new Text(shell, SWT.BORDER);
		
		text_2.setBounds(153, 75, 131, 23);
		
		final Label label = new Label(shell, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label.setBounds(296, 78, 134, 17);
		
		final Label label_1 = new Label(shell, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_1.setBounds(290, 123, 171, 17);
		
		final CustomerInfoDBH customersInfoDBH=new CustomerInfoDBH();
		final String accountId=UiCollections.currentLoginUser.get("accountid");
		final Map<String,String> map=customersInfoDBH.find(accountId);
		text_1.setText(map.get("question"));
		
		//输入密保答案
		text_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String answer=text_2.getText().trim();
				if(answer==null || "".equals(answer)){
					label.setText("请输入密保答案");
				}else if(answer.equals(map.get("answer"))){
					label.setText("");
				}else{
					label.setText("密保答案不正确，请重新输入");
				}
			}
		});
		//新的邮箱账号
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String email=text.getText().trim();
				p=Pattern.compile("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$");
				str=p.matcher(email);
				b=str.matches();
				if(email==null || "".equals(email)){
					label_1.setText("请输入新的邮箱账号");
				}else if(b){
					label_1 .setText("√");
				}else{
					label_1.setText("账号格式不正确，请重新输入");
				}
			}
		});
		
		//点击确认
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String email=text.getText().trim();
				if(customersInfoDBH.alter_mail(accountId, email)>0){
					MessageDialog.openInformation(shell, "成功提示", "修改邮箱账号成功...");
					shell.close();
				}else{
					MessageDialog.openError(shell, "失败提示", "修改邮箱账号失败！！！");
				}
				
			}
		});
		
		
		
		
	}
}
