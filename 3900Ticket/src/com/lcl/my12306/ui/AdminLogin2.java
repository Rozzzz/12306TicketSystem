package com.lcl.my12306.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.layout.FillLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.lcl.my12306.dao.AdminsinfoDBH;
import com.lcl.my12306.dao.DBHelper;
import com.lcl.my12306.utils.MD5AndKillAction;
import com.lcl.my12306.utils.UiCollections;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AdminLogin2 extends Composite {
	protected Shell shell ;
	private Text text;
	private Text text_1;
	private int x;
	private int y;
	private boolean isDown=false;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public AdminLogin2(final Composite parent, int style) {
		super(parent, style);
		setBackgroundImage(SWTResourceManager.getImage(AdminLogin2.class, "/image/back3.jpg"));
	
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.NORMAL));
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("华文行楷", 30, SWT.BOLD));
		lblNewLabel.setBounds(168, 49, 231, 42);
		lblNewLabel.setText("管理员登录");
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		lblNewLabel_1.setBounds(81, 146, 95, 24);
		lblNewLabel_1.setText("用户名：");
		
		Label lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("微软雅黑", 14, SWT.NORMAL));
		lblNewLabel_2.setBounds(93, 197, 83, 24);
		lblNewLabel_2.setText("密 码：");
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(190, 146, 179, 24);
		
		text_1 = new Text(this, SWT.BORDER | SWT.PASSWORD);
		text_1.setBounds(190, 201, 179, 24);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		
		btnNewButton.setBounds(115, 268, 80, 27);
		btnNewButton.setText("登录");
		
		Button btnNewButton_1 = new Button(this, SWT.NONE);
		
		btnNewButton_1.setBounds(249, 268, 80, 27);
		btnNewButton_1.setText("取消");
		
		
		
		//点击登录
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AdminsinfoDBH adminsinfoDBH=new AdminsinfoDBH();
				String a_ID=text.getText().trim();
				String a_pwd=text_1.getText().trim();
				if(adminsinfoDBH.landing(a_ID, a_pwd)){
					Map<String,String> map=new HashMap<String,String>();
					map.put("a_id", a_ID);
					map.put("a_pwd", a_pwd);
					UiCollections.currentLoginUser=map;
					Daily_report daily_report=new Daily_report();
					daily_report.open();
					UiCollections.homePage.setVisible(true);
					UiCollections.adminLogin2.setVisible(false);
				}else{
					MessageDialog.openError(parent.getShell(), "失败提示", "用户名或密码错误");
				}
			}
		});
		
//		//拖动面板
//		addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseDown(MouseEvent e) {
//				isDown=true;
//				x=e.x;
//				y=e.y;
//				
//			}
//			@Override
//			public void mouseUp(MouseEvent e) {
//				isDown=false;
//				
//				
//			}
//		});

//		addMouseMoveListener(new MouseMoveListener() {//监听鼠标移动
//			public void mouseMove(MouseEvent e) {
//				if(isDown){//获取光标位置
//					shell.setLocation(shell.getLocation().x+e.x-x,shell.getLocation().y+e.y-y);
//				}
//			}
//
//		});
		//取消
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				UiCollections.adminLogin2.setVisible(false);
				UiCollections.homePage.setVisible(true);
				UiCollections.stackLayout.topControl=UiCollections.homePage;
			}
		});
		
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
