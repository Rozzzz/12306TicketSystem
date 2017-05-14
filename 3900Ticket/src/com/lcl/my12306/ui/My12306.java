package com.lcl.my12306.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import com.lcl.my12306.utils.UiCollections;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.MouseMoveListener;

public class My12306 {

	protected static Shell shell;
	private Display display;
	private boolean isDown=false;//记录鼠标有没有按下去
	private int x;  //鼠标按下去时x轴坐标
	private int y;  //鼠标按下去时y轴坐标

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			My12306 window = new My12306();
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
		shell = new Shell(SWT.NONE);
		shell.setImage(SWTResourceManager.getImage(My12306.class, "/image/12306_logo.jpg"));
		shell.setSize(956, 630);
		shell.setText("12306");
		shell.setLocation((display.getClientArea().width-shell.getSize().x)/2, (display.getClientArea().height-shell.getSize().y)/2);
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(shell, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackgroundImage(SWTResourceManager.getImage(My12306.class, "/image/12306.png"));
		composite.setLayout(null);
		
		final Label lblNewLabel = new Label(composite, SWT.NONE);
		
		lblNewLabel.setImage(SWTResourceManager.getImage(My12306.class, "/image/btn_close_normal.png"));
		lblNewLabel.setBounds(914, 0, 39, 20);
		
		final Label lblNewLabel_5 = new Label(composite, SWT.NONE);
		
		lblNewLabel_5.setImage(SWTResourceManager.getImage(My12306.class, "/image/btn_mini_normal.png"));
		lblNewLabel_5.setBounds(886, 0, 28, 20);
		
		Label label = new Label(composite, SWT.NONE);
		
		label.setBounds(905, 135, 48, 17);
		label.setText("退出登录");
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_1 = new SashForm(composite_1, SWT.NONE);
		
		Composite composite_2 = new Composite(sashForm_1, SWT.NONE);
		composite_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		composite_2.setLayout(null);
		
		Label lblNewLabel_1 = new Label(composite_2, SWT.NONE);
		lblNewLabel_1.setImage(SWTResourceManager.getImage(My12306.class, "/image/a1.png"));
		lblNewLabel_1.setBounds(0, 0, 41, 36);
		
		Button btnNewButton = new Button(composite_2, SWT.NONE);
		
		btnNewButton.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		btnNewButton.setBounds(42, 0, 158, 36);
		btnNewButton.setText("用户注册");
		
		Label lblNewLabel_2 = new Label(composite_2, SWT.NONE);
		lblNewLabel_2.setImage(SWTResourceManager.getImage(My12306.class, "/image/a1.png"));
		lblNewLabel_2.setBounds(0, 38, 41, 36);
		
		Button btnNewButton_1 = new Button(composite_2, SWT.NONE);
		
		btnNewButton_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		btnNewButton_1.setBounds(42, 36, 158, 38);
		btnNewButton_1.setText("用户登录");
		
		Label lblNewLabel_3 = new Label(composite_2, SWT.NONE);
		lblNewLabel_3.setImage(SWTResourceManager.getImage(My12306.class, "/image/a2.png"));
		lblNewLabel_3.setBounds(0, 75, 41, 36);
		
		Button btnNewButton_2 = new Button(composite_2, SWT.NONE);
		
		btnNewButton_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		btnNewButton_2.setBounds(42, 75, 158, 36);
		btnNewButton_2.setText("购    票");
		
		Label lblNewLabel_4 = new Label(composite_2, SWT.NONE);
		lblNewLabel_4.setImage(SWTResourceManager.getImage(My12306.class, "/image/a3.png"));
		lblNewLabel_4.setBounds(0, 112, 41, 36);
		
		Button btnNewButton_3 = new Button(composite_2, SWT.NONE);
		
		btnNewButton_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		btnNewButton_3.setBounds(42, 112, 158, 36);
		btnNewButton_3.setText("退    票");
		
		Label lblNewLabel_6 = new Label(composite_2, SWT.NONE);
		lblNewLabel_6.setImage(SWTResourceManager.getImage(My12306.class, "/image/a5.png"));
		lblNewLabel_6.setBounds(0, 149, 41, 36);
		
		Button btnNewButton_4 = new Button(composite_2, SWT.NONE);
		
		btnNewButton_4.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		btnNewButton_4.setBounds(42, 149, 158, 36);
		btnNewButton_4.setText("余票查询");
		
		Label lblNewLabel_7 = new Label(composite_2, SWT.NONE);
		lblNewLabel_7.setImage(SWTResourceManager.getImage(My12306.class, "/image/a6.png"));
		lblNewLabel_7.setBounds(0, 186, 41, 36);
		
		Button btnNewButton_5 = new Button(composite_2, SWT.NONE);
		
		btnNewButton_5.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		btnNewButton_5.setBounds(42, 186, 158, 36);
		btnNewButton_5.setText("票价查询");
		
		Label lblNewLabel_9 = new Label(composite_2, SWT.NONE);
		lblNewLabel_9.setImage(SWTResourceManager.getImage(My12306.class, "/image/a9.png"));
		lblNewLabel_9.setBounds(0, 222, 41, 36);
		
		Button btnNewButton_7 = new Button(composite_2, SWT.NONE);
		
		btnNewButton_7.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		btnNewButton_7.setBounds(42, 222, 158, 36);
		btnNewButton_7.setText("查看个人信息");
		
		Label lblNewLabel_10 = new Label(composite_2, SWT.NONE);
		lblNewLabel_10.setImage(SWTResourceManager.getImage(My12306.class, "/image/a8.png"));
		lblNewLabel_10.setBounds(0, 258, 41, 36);
		
		Button btnNewButton_8 = new Button(composite_2, SWT.NONE);
		
		btnNewButton_8.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		btnNewButton_8.setBounds(42, 258, 158, 36);
		btnNewButton_8.setText("管理员登录");
		
		Button button = new Button(composite_2, SWT.NONE);
		button.setText("日志查询");
		button.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		button.setBounds(42, 296, 158, 36);
		
		Label label_1 = new Label(composite_2, SWT.NONE);
		label_1.setImage(SWTResourceManager.getImage(My12306.class, "/image/a7.png"));
		label_1.setBounds(0, 296, 41, 36);
		
		Composite composite_3 = new Composite(sashForm_1, SWT.NONE);
		
		//将右侧的内容显示面板设置为堆栈式布局
		composite_3.setLayout(UiCollections.stackLayout);
		
		sashForm_1.setWeights(new int[] {203, 748});
		sashForm.setWeights(new int[] {149, 462});
		HomePage homePage = new HomePage(composite_3, SWT.NONE);
		UiCollections.homePage=homePage;
		
		
		//初始化面板
		Register register = new Register(homePage, SWT.NONE);
		UiCollections.register=new Register(composite_3, SWT.NONE);
		UiCollections.homePage=new HomePage(composite_3, SWT.NONE);
		UiCollections.remainTicket=new RemainTicket(composite_3, SWT.NONE);
		UiCollections.ticketPrice=new TicketPrice(composite_3, SWT.NONE);
		UiCollections.userLogin=new UserLogin(composite_3, SWT.NONE);
		UiCollections.adminLogin=new AdminLogin(composite_3, SWT.NONE);
		UiCollections.adminLogin2=new AdminLogin2(composite_3, SWT.NONE);
		
				
		//开始运行时最上面的面板
		UiCollections.stackLayout.topControl=UiCollections.homePage;
		
		//点击用户注册
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				UiCollections.homePage.setVisible(false);
				UiCollections.remainTicket.setVisible(false);
				UiCollections.ticketPrice.setVisible(false);
				UiCollections.userLogin.setVisible(false);
				UiCollections.adminLogin.setVisible(false);
				UiCollections.register.setVisible(true);
				UiCollections.adminLogin2.setVisible(false);
				UiCollections.stackLayout.topControl=UiCollections.register;
					
			}
		});
		
		//点击用户登录
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				UiCollections.homePage.setVisible(false);
				UiCollections.remainTicket.setVisible(false);
				UiCollections.ticketPrice.setVisible(false);
				UiCollections.userLogin.setVisible(true);
				UiCollections.adminLogin.setVisible(false);
				UiCollections.register.setVisible(false);
				UiCollections.adminLogin2.setVisible(false);
				UiCollections.stackLayout.topControl=UiCollections.userLogin;
			}
		});
		
		//点击管理员登录
		btnNewButton_8.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				UiCollections.homePage.setVisible(false);
				UiCollections.remainTicket.setVisible(false);
				UiCollections.ticketPrice.setVisible(false);
				UiCollections.userLogin.setVisible(false);
				UiCollections.adminLogin.setVisible(true);
				UiCollections.register.setVisible(false);
				UiCollections.adminLogin2.setVisible(false);
				UiCollections.stackLayout.topControl=UiCollections.adminLogin;
			}
		});
		
		//点击购票
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				BuyTicket buyTicket=new BuyTicket();
				buyTicket.open();
			}
		});
		
		//点击余票查询
		btnNewButton_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				UiCollections.homePage.setVisible(false);
				UiCollections.remainTicket.setVisible(true);
				UiCollections.ticketPrice.setVisible(false);
				UiCollections.userLogin.setVisible(false);
				UiCollections.adminLogin.setVisible(false);
				UiCollections.register.setVisible(false);
				UiCollections.adminLogin2.setVisible(false);
				UiCollections.stackLayout.topControl=UiCollections.remainTicket;
			}
		});
		
		//点击票价查询
		btnNewButton_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				UiCollections.homePage.setVisible(false);
				UiCollections.remainTicket.setVisible(false);
				UiCollections.ticketPrice.setVisible(true);
				UiCollections.userLogin.setVisible(false);
				UiCollections.adminLogin.setVisible(false);
				UiCollections.register.setVisible(false);
				UiCollections.adminLogin2.setVisible(false);
				UiCollections.stackLayout.topControl=UiCollections.ticketPrice;
			}
		});
		
		//点击日志查询
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				UiCollections.homePage.setVisible(false);
				UiCollections.remainTicket.setVisible(false);
				UiCollections.ticketPrice.setVisible(false);
				UiCollections.userLogin.setVisible(false);
				UiCollections.adminLogin.setVisible(false);
				UiCollections.register.setVisible(false);
				UiCollections.adminLogin2.setVisible(true);
				UiCollections.stackLayout.topControl=UiCollections.adminLogin2;
			
			}
		});
		
		//点击退票
		btnNewButton_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				UserInfo userInfo=new UserInfo();
				shell.dispose();
				userInfo.open();
			}
		});
		
		//点击个人信息
		btnNewButton_7.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				UserInfo userInfo=new UserInfo();
				shell.dispose();
				userInfo.open();
			}
		});

		
		//面板拖动
		composite.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent e) {//鼠标移动的时候
				//获取此时的光标位置
				if(isDown){
					shell.setLocation(shell.getLocation().x+e.x-x,shell.getLocation().y+e.y-y);
				}
			}
		});
		composite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {//鼠标按下的时候
				isDown=true;
				x=e.x;
				y=e.y;
			}
			@Override
			public void mouseUp(MouseEvent e) {//鼠标松开的时候
				isDown=false;
			}
		});
		//关闭按钮
		lblNewLabel.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseExit(MouseEvent e) { //鼠标移开
				lblNewLabel.setImage(SWTResourceManager.getImage(My12306.class, "/image/btn_close_normal.png"));
			}
			@Override
			public void mouseHover(MouseEvent e) { //鼠标移上
				lblNewLabel.setImage(SWTResourceManager.getImage(My12306.class, "/image/btn_close_highlight.png"));
			}
		});
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) { //鼠标按下
				lblNewLabel.setImage(SWTResourceManager.getImage(My12306.class, "/image/btn_close_down.png"));
			}
			@Override
			public void mouseUp(MouseEvent e) { //鼠标松开
				if(MessageDialog.openConfirm(shell, "关闭提示", "您确定要退出12306吗？")){
					shell.dispose();
				}
			}
		});
		
		//最小化按钮
		lblNewLabel_5.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseExit(MouseEvent e) { //鼠标移开
				lblNewLabel_5.setImage(SWTResourceManager.getImage(My12306.class, "/image/btn_mini_normal.png"));
			}
			@Override
			public void mouseHover(MouseEvent e) { //鼠标移上
				lblNewLabel_5.setImage(SWTResourceManager.getImage(My12306.class, "/image/btn_mini_highlight.png"));
			}
		});
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) { //鼠标按下
				lblNewLabel_5.setImage(SWTResourceManager.getImage(My12306.class, "/image/btn_mini_down.png"));
			}
			@Override
			public void mouseUp(MouseEvent e) { //鼠标松开
				shell.setMinimized(true);
			}
		});
		
		//退出登录
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				UiCollections.homePage.setVisible(true);
				UiCollections.remainTicket.setVisible(false);
				UiCollections.ticketPrice.setVisible(false);
				UiCollections.userLogin.setVisible(false);
				UiCollections.adminLogin.setVisible(false);
				UiCollections.register.setVisible(false);
				UiCollections.stackLayout.topControl=UiCollections.homePage;
			}
		});
	}
}
