package com.lcl.my12306.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import com.lcl.my12306.dao.ContactInfoDao;
import com.lcl.my12306.utils.UiCollections;

import org.eclipse.swt.custom.SashForm;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.custom.StackLayout;

public class UserInfo {


	protected static Shell shell;
	private Display display;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UserInfo window = new UserInfo();
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
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(UserInfo.class, "/image/12306_logo.jpg"));
		shell.setSize(450, 300);
		shell.setText("个人信息      当前用户："+UiCollections.currentLoginUser.get("accountid"));
		shell.setBounds(0, 0, display.getClientArea().width, display.getClientArea().height);
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(shell, SWT.NONE);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackgroundImage(SWTResourceManager.getImage(UserInfo.class, "/image/b13.jpg"));
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		
		Label label = new Label(composite, SWT.NONE);
		
		label.setBackground(SWTResourceManager.getColor(245, 222, 179));
		label.setAlignment(SWT.CENTER);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label.setBounds(68, 54, 109, 26);
		label.setText("查看个人信息");
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		
		lblNewLabel.setBackground(SWTResourceManager.getColor(245, 222, 179));
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		lblNewLabel.setBounds(68, 123, 109, 26);
		lblNewLabel.setText("订单详情");
		
		Label lblNewLabel_1 = new Label(composite, SWT.CENTER);
		
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(245, 222, 179));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		lblNewLabel_1.setBounds(68, 198, 109, 26);
		lblNewLabel_1.setText("常用联系人");
		
		Label label_1 = new Label(composite, SWT.NONE);
		
		label_1.setBackground(SWTResourceManager.getColor(245, 222, 179));
		label_1.setAlignment(SWT.CENTER);
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_1.setBounds(68, 264, 109, 26);
		label_1.setText("添加联系人");
		
		Label label_2 = new Label(composite, SWT.NONE);
		
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_2.setAlignment(SWT.CENTER);
		label_2.setBackground(SWTResourceManager.getColor(245, 222, 179));
		label_2.setBounds(68, 342, 109, 26);
		label_2.setText("返回首页");
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		sashForm.setWeights(new int[] {230, 1117});
		composite_1.setLayout(UiCollections.stackLayout);
		
		//初始化面板
		UiCollections.contactInfo=new ContactInfo(composite_1, SWT.NONE);
		UiCollections.myInfo=new MyInfo(composite_1, SWT.NONE);
		UiCollections.order=new Order(composite_1, SWT.NONE);
		
		UiCollections.stackLayout.topControl=UiCollections.myInfo;
		
		//个人信息
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				UiCollections.myInfo.setVisible(true);
				UiCollections.contactInfo.setVisible(false);
				UiCollections.order.setVisible(false);
				UiCollections.stackLayout.topControl=UiCollections.myInfo;
			}
		});

		//点击常用联系人
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				UiCollections.contactInfo.setVisible(true);
				UiCollections.order.setVisible(false);
				UiCollections.myInfo.setVisible(false);
				UiCollections.stackLayout.topControl=UiCollections.contactInfo;
				ContactInfo.showInfo();
		
			}
		});
		
		//点击订单详情
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				UiCollections.contactInfo.setVisible(false);
				UiCollections.order.setVisible(true);
				UiCollections.myInfo.setVisible(false);
				UiCollections.stackLayout.topControl=UiCollections.order;		
			}
		});
		
		//点击添加联系人
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				AddContact addContact=new AddContact();
				addContact.open();
			}
		});
		
		//返回首页
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				My12306 my12306=new My12306();
				shell.dispose();
				my12306.open();
			}
		});
	}
	
	
}
