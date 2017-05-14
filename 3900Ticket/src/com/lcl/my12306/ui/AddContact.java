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

import com.lcl.my12306.dao.ContactInfoDao;
import com.lcl.my12306.utils.UiCollections;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.internal.C;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class AddContact {

	protected Shell shell;
	private Text text;
	private Text text_2;
	private Display display;
	private Pattern p;//正则表达式
	private Matcher str;//操作的字符串
	private boolean b=false;
	private Text text_1;
	private Text text_3;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AddContact window = new AddContact();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
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
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setImage(SWTResourceManager.getImage(AddContact.class, "/image/12306_logo.jpg"));
		shell.setSize(488, 346);
		shell.setText("添加联系人");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		//居中显示
		shell.setLocation((display.getClientArea().width-shell.getSize().x)/2,(display.getClientArea().height-shell.getSize().y-30)/2);

		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBackgroundImage(SWTResourceManager.getImage(AddContact.class, "/image/back4.jpg"));
		composite.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBounds(78, 76, 43, 17);
		lblNewLabel.setText("姓  名：");
		
		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.setBounds(62, 110, 61, 17);
		lblNewLabel_2.setText("身份证号：");
		
		text = new Text(composite, SWT.BORDER);
		
		text.setBounds(136, 70, 140, 23);
		
		text_2 = new Text(composite, SWT.BORDER);
		
		text_2.setBounds(136, 107, 140, 23);
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		
		btnNewButton.setBounds(109, 251, 80, 27);
		btnNewButton.setText("添加");
		
		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		
		btnNewButton_1.setBounds(240, 251, 80, 27);
		btnNewButton_1.setText("重置");
		
		final Label lblNewLabel_4 = new Label(composite, SWT.NONE);
		lblNewLabel_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_4.setBounds(296, 72, 110, 17);
		
		final Label lblNewLabel_6 = new Label(composite, SWT.NONE);
		lblNewLabel_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_6.setBounds(296, 110, 110, 17);
		
		Label lblNewLabel_7 = new Label(composite, SWT.NONE);
		lblNewLabel_7.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		lblNewLabel_7.setBounds(157, 24, 95, 23);
		lblNewLabel_7.setText("添加联系人");
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setBounds(51, 140, 72, 17);
		label_1.setText("是否是学生：");
		
		final Button button = new Button(composite, SWT.RADIO);
		
		button.setBounds(136, 140, 43, 17);
		button.setText("是");
		
		final Button button_1 = new Button(composite, SWT.RADIO);
		
		button_1.setBounds(209, 140, 43, 17);
		button_1.setText("否");
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setBounds(62, 172, 61, 17);
		label_2.setText("学校名称：");
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setBounds(77, 206, 43, 17);
		label_3.setText("学  号：");
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setBounds(136, 203, 140, 23);
		
		text_3 = new Text(composite, SWT.BORDER);
		text_3.setBounds(136, 169, 140, 23);
		
		//输入姓名时
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String name=text.getText().trim();
				if(name==null || "".equals(name)){
					lblNewLabel_4.setText("请输入姓名");
				}else{
					lblNewLabel_4.setText("");
				}
			}
		});
		
		//输入身份证时
		text_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String L_ID=text_2.getText().trim();
				p=Pattern.compile("^([0-9]{6})(18|19|20)?([0-9]{2})([01][0-9])([0123][0-9])([0-9]{3})([0-9]|X|x)?$");
				str=p.matcher(L_ID);
				b=str.matches();	
				if(L_ID==null || "".equals(L_ID)){
					lblNewLabel_6.setText("请输入身份证号");
				}else if(b){
					lblNewLabel_6.setText("");
				}else{
					lblNewLabel_6.setText("请正确输入身份证号");
				}
			}
		});
		
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text_1.setEnabled(true);
				text_3.setEnabled(true);		
			}
		});
		
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text_1.setEnabled(false);
				text_3.setEnabled(false);
				
			}
		});
		
		
		
		//添加
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String l_name=text.getText().trim();
				String isStudent=null;
				String l_id=text_2.getText().trim();
				String school=text_3.getText().trim();
				String stu_id=text_1.getText().trim();
				String accountid=UiCollections.currentLoginUser.get("accountid");
				if(button.getSelection()){
					isStudent=button.getText().trim();
				}else{
					isStudent=button_1.getText().trim();
				}
				
				//将数据存到数据库
				ContactInfoDao contactInfoDao=new ContactInfoDao();
				if(contactInfoDao.add(l_id, l_name, isStudent, accountid, school, stu_id)>0){
					MessageDialog.openInformation(shell, "成功提示", "添加联系人成功");
					ContactInfo.showInfo();
					text.setText("");
					text_2.setText("");
					text_3.setText("");
					text_1.setText("");
					
				}else{
					MessageDialog.openError(shell, "失败提示", "添加联系人失败");
				}				
			}
		});
		
		//重置
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				text.setText("");
				text_2.setText("");
				text_3.setText("");
				text_1.setText("");
				lblNewLabel_6.setText("");
				lblNewLabel_4.setText("");
			}
		});

	}
}
