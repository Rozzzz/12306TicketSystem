package com.lcl.my12306.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import com.lcl.my12306.dao.CustomerInfoDBH;
import com.lcl.my12306.utils.MD5AndKillAction;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class CompleteInfo {

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
			CompleteInfo window = new CompleteInfo();
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
		shell = new Shell(SWT.APPLICATION_MODAL|SWT.CLOSE);
		shell.setImage(SWTResourceManager.getImage(CompleteInfo.class, "/image/12306_logo.jpg"));
		shell.setSize(596, 422);
		shell.setText("完善个人信息");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackgroundImage(SWTResourceManager.getImage(CompleteInfo.class, "/image/back4.jpg"));
		
		Label label = new Label(composite, SWT.NONE);
		label.setText("完善个人信息");
		label.setFont(SWTResourceManager.getFont("华文行楷", 30, SWT.NORMAL));
		label.setBounds(162, 27, 247, 42);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setText("  姓  名：");
		label_1.setBounds(140, 100, 61, 17);
		
		text = new Text(composite, SWT.BORDER);
		
		text.setMessage("请输入您的真实姓名");
		text.setBounds(211, 98, 181, 23);
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setText("身份证号：");
		label_2.setBounds(134, 140, 61, 17);
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setMessage("请输入您的身份证号");
		text_1.setBounds(213, 136, 181, 23);
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setText("  性  别：");
		label_3.setBounds(141, 173, 61, 17);
		
		final Button button = new Button(composite, SWT.RADIO);
		button.setText("男");
		button.setBounds(248, 176, 43, 17);
		
		final Button button_1 = new Button(composite, SWT.RADIO);
		button_1.setText("女");
		button_1.setBounds(317, 177, 43, 17);
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setText("支付密码：");
		label_4.setBounds(140, 215, 61, 17);
		
		text_2 = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		
		text_2.setMessage("请输入6位数字的支付密码");
		text_2.setBounds(212, 212, 180, 23);
		
		Label label_5 = new Label(composite, SWT.CENTER);
		
		label_5.setText("提  交");
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.BOLD));
		label_5.setBackground(SWTResourceManager.getColor(245, 222, 179));
		label_5.setBounds(243, 290, 116, 28);
		
		final Label label_6 = new Label(composite, SWT.NONE);
		label_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_6.setBounds(427, 100, 131, 17);
		
		final Label label_7 = new Label(composite, SWT.NONE);
		label_7.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_7.setBounds(427, 140, 131, 17);
		
		final Label label_8 = new Label(composite, SWT.NONE);
		label_8.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_8.setBounds(427, 215, 131, 17);
		
		//输入姓名
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String cname=text.getText().trim();
				if(cname==null || "".equals(cname)){
					label_6.setText("请输入姓名");
				}else{
					label_6.setText("√");
				}
			}
		});
		
		//输入身份证号
		text_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String c_ID=text_1.getText().trim();
				p=Pattern.compile("^([0-9]{6})(18|19|20)?([0-9]{2})([01][0-9])([0123][0-9])([0-9]{3})([0-9]|X|x)?$");
				str=p.matcher(c_ID);
				b=str.matches();	
				if(c_ID==null || "".equals(c_ID)){
					label_7.setText("请输入身份证号");
				}else if(b){
					label_7.setText("√");
				}else{
					label_7.setText("请正确输入身份证号");
				}
			}
		});
		
		
		//支付密码
		text_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String p_pwd=text_2.getText().trim();
				p=Pattern.compile("[0-9]{6}");
				str=p.matcher(p_pwd);
				b=str.matches();
				if(p_pwd==null || "".equals(p_pwd)){
					label_8.setText("请输入支付密码");
				}else if(b){
					label_8.setText("√");
				}else{
					label_8.setText("请输入6位数字的支付密码");
				}
				
			}
		});
		
		
		//按下提交
		label_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				MD5AndKillAction md=new MD5AndKillAction();
				String cname=text.getText().trim();
				String p_pwd=md.KL(md.MD5(text_2.getText().trim()));
				String c_ID=text_1.getText().trim();
				String accountId=Register.accountId;
				CustomerInfoDBH customerInfoDBH=new CustomerInfoDBH();
				String sex;
				if(button_1.getSelection()){
					sex=button_1.getText().trim();
				}else{
					sex=button.getText().trim();
				}
				if(customerInfoDBH.add2(accountId,cname, c_ID, sex, p_pwd)>0){
					MessageDialog.openInformation(shell, "成功提示", "个人信息完善成功");
					shell.close();
					
				}else{
					MessageDialog.openError(shell, "失败提示", "个人信息完善失败");
				}
			}
		});
	}
}
