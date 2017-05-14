package com.lcl.my12306.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.lcl.my12306.dao.CustomerInfoDBH;
import com.lcl.my12306.utils.UiCollections;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class IsStudent {

	protected Shell shell;
	private Text text;
	private Text text_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			IsStudent window = new IsStudent();
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
		shell.setImage(SWTResourceManager.getImage(IsStudent.class, "/image/12306_logo.jpg"));
		shell.setSize(418, 240);
		shell.setText("学生验证");
		
		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label.setBounds(56, 43, 61, 17);
		label.setText("学校名称：");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(123, 43, 149, 23);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_1.setBounds(65, 91, 52, 17);
		label_1.setText("学    号：");
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(123, 89, 149, 23);
		
		Button button = new Button(shell, SWT.NONE);
		button.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		
		button.setBounds(158, 152, 80, 27);
		button.setText("提交");
		
		//提交
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String school=text.getText().trim();
				String stu_ID=text_1.getText().trim();
				String accountId=UiCollections.currentLoginUser.get("accountid");
				CustomerInfoDBH customerInfoDBH=new CustomerInfoDBH();
				if(customerInfoDBH.alter_stu(accountId, school, stu_ID)>0){
					MessageDialog.openInformation(shell, "成功提示", "验证成功，您将可以购买学生票。");
					shell.close();
				}else{
					MessageDialog.openError(shell, "失败提示", "验证失败，您不可以购买学生票。");
				}
				
			}
		});

	}
}
