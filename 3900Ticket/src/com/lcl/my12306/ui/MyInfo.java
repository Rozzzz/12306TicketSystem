package com.lcl.my12306.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import com.lcl.my12306.dao.CustomerInfoDBH;
import com.lcl.my12306.utils.UiCollections;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.SashForm;

public class MyInfo extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public MyInfo(Composite parent, int style) {
		super(parent, SWT.BORDER);
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setBackgroundImage(SWTResourceManager.getImage(MyInfo.class, "/image/back4.jpg"));
		
		Label label = new Label(this, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label.setBounds(244, 46, 61, 21);
		label.setText("用户名");
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		lblNewLabel.setBounds(342, 46, 181, 21);
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_1.setBounds(234, 254, 71, 21);
		label_1.setText("邮箱账号");
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		lblNewLabel_1.setBounds(342, 254, 181, 21);
		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_2.setBounds(244, 83, 61, 21);
		label_2.setText("姓  名");
		
		Label lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		lblNewLabel_2.setBounds(342, 84, 181, 20);
		
		Label label_3 = new Label(this, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_3.setBounds(232, 125, 71, 26);
		label_3.setText("身份证号");
		
		Label lblNewLabel_3 = new Label(this, SWT.NONE);
		lblNewLabel_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		lblNewLabel_3.setBounds(342, 125, 181, 22);
		
		Label label_4 = new Label(this, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_4.setBounds(244, 167, 51, 21);
		label_4.setText("性  别");
		
		Label lblNewLabel_4 = new Label(this, SWT.NONE);
		lblNewLabel_4.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		lblNewLabel_4.setBounds(342, 167, 181, 21);
		
		Label label_5 = new Label(this, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_5.setBounds(232, 208, 71, 26);
		label_5.setText("密保问题");
		
		Label lblNewLabel_5 = new Label(this, SWT.NONE);
		lblNewLabel_5.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		lblNewLabel_5.setBounds(342, 209, 181, 21);
		
		Button button = new Button(this, SWT.NONE);
		
		button.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		button.setBounds(549, 252, 90, 27);
		button.setText("修改邮箱账号");
		
		//显示个人信息
		CustomerInfoDBH customerInfoDBH=new CustomerInfoDBH();
		Map<String,String> map=customerInfoDBH.find(UiCollections.currentLoginUser.get("accountid"));
		lblNewLabel.setText(UiCollections.currentLoginUser.get("accountid"));
		lblNewLabel_1.setText(map.get("email"));
		lblNewLabel_2.setText(map.get("cname"));
		lblNewLabel_3.setText(map.get("c_id"));
		lblNewLabel_4.setText(map.get("sex"));
		lblNewLabel_5.setText(map.get("question"));
		
		Button button_1 = new Button(this, SWT.NONE);
		
		button_1.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		button_1.setBounds(295, 338, 102, 27);
		button_1.setText("修改登录密码");
		
		Button button_2 = new Button(this, SWT.NONE);
		
		button_2.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		button_2.setBounds(427, 338, 102, 27);
		button_2.setText("修改支付密码");
		
		Button button_3 = new Button(this, SWT.NONE);
		
		button_3.setBounds(561, 339, 80, 27);
		button_3.setText("申请学生");
		//lblNewLabel_6.setText(map.get("isstudent"));
		
		//修改登录密码
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				UpdatePwd updatePwd=new UpdatePwd();
				updatePwd.open();
			}
		});
		
		//修改支付密码
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				UpdatePayPwd updatePayPwd=new UpdatePayPwd();
				updatePayPwd.open();
			}
		});
		
		//修改邮箱账号
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				UpdateEmail updateEmail=new UpdateEmail();
				updateEmail.open();
			}
		});
		//申请为学生
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IsStudent isStudent=new IsStudent();
				isStudent.open();
			}
		});

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
