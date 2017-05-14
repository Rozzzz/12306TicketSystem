package com.lcl.my12306.ui;

import org.eclipse.swt.widgets.Composite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Link;
import org.eclipse.wb.swt.SWTResourceManager;

import com.lcl.my12306.dao.DBHelper;
import com.lcl.my12306.utils.MD5AndKillAction;
import com.lcl.my12306.utils.RegisterUtils;
import com.lcl.my12306.utils.UiCollections;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class UserLogin extends Composite {
	private Text text;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public UserLogin(final Composite parent, int style) {
		super(parent, style);
		setBackgroundImage(SWTResourceManager.getImage(UserLogin.class, "/image/back1.jpg"));
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("黑体", 13, SWT.NORMAL));
		lblNewLabel.setBounds(210, 141, 80, 24);
		lblNewLabel.setText("用户名：");
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("黑体", 13, SWT.NORMAL));
		lblNewLabel_1.setBounds(210, 198, 80, 24);
		lblNewLabel_1.setText("密  码：");
		
		text = new Text(this, SWT.BORDER | SWT.PASSWORD);
		text.setBounds(296, 198, 149, 23);
		
		final Combo combo = new Combo(this, SWT.NONE);
		combo.setBounds(296, 141, 149, 25);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		
		btnNewButton.setBounds(231, 301, 80, 27);
		btnNewButton.setText("登录");
		
		Button btnNewButton_1 = new Button(this, SWT.NONE);
		
		btnNewButton_1.setBounds(371, 301, 80, 27);
		btnNewButton_1.setText("取消");
		
		final Button button = new Button(this, SWT.CHECK);
		button.setBounds(255, 248, 80, 17);
		button.setText("记住密码");
		
		Link link = new Link(this, SWT.NONE);
		
		link.setBounds(348, 248, 80, 17);
		link.setText("<a>忘记密码</a>");
		
		Label lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("华文行楷", 30, SWT.BOLD));
		lblNewLabel_2.setBounds(272, 58, 173, 42);
		lblNewLabel_2.setText("用户登录");
		
		//点击登录
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MD5AndKillAction md=new MD5AndKillAction();
				String accountId=combo.getText().trim();
				String e_pwd=md.KL(md.MD5(text.getText().trim()));
				DBHelper db=new DBHelper();
				String sql="select *from customers where accountId=? and e_pwd=?";
				ArrayList<Object> params=new ArrayList<Object>();
				params.add(accountId);
				params.add(e_pwd);
				Map<String,String> map=db.find(sql, params);
				 if(map!=null && map.size()>0){
					//判断是否需要记住用户名和密码
					if(button.getSelection()){//如需记住，则写入注册表
						System.out.println(button.getSelection());
						Map<String,String> map1=new HashMap<String,String>();
						map1.put(accountId, e_pwd);
						RegisterUtils.add(map1);					
					}else{//说明不需要写入注册表
						System.out.println(button.getSelection());
						RegisterUtils.remove(accountId);
					}
					
					//将当前成功登陆的用户存到一个共享变量中
					UiCollections.currentLoginUser=map;
					UiCollections.userLogin.setVisible(false);
					UiCollections.homePage.setVisible(true);
					UiCollections.stackLayout.topControl=UiCollections.homePage;
				}else{
					MessageDialog.openError(parent.getShell(), "失败提示", "用户名或密码错误，请确认后重新输入。。。");
				}				
			}
		});
		
		
		//取消
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				UiCollections.userLogin.setVisible(false);
				UiCollections.homePage.setVisible(true);
				UiCollections.stackLayout.topControl=UiCollections.homePage;
			}
		});
		
		//忘记密码
		link.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
			}
		});

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
