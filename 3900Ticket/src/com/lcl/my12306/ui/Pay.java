package com.lcl.my12306.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.wb.swt.SWTResourceManager;

import com.lcl.my12306.dao.DBHelper;
import com.lcl.my12306.dao.ticketOrderInfoDBH;
import com.lcl.my12306.utils.MD5AndKillAction;
import com.lcl.my12306.utils.UiCollections;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Pay {

	protected Shell shell;
	private Text text;
	private static String info;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Pay window = new Pay();
			window.open(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open(String info) {
		this.info=info;
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
		shell.setImage(SWTResourceManager.getImage(Pay.class, "/image/12306_logo.jpg"));
		shell.setSize(436, 285);
		shell.setText("我是来收钱的");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		
		Label label = new Label(composite, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.NORMAL));
		label.setBounds(28, 114, 126, 33);
		label.setText("请输入支付密码：");
		
		text = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		text.setBounds(179, 116, 195, 23);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.NORMAL));
		lblNewLabel.setBounds(109, 46, 201, 33);
		
		Button button = new Button(composite, SWT.NONE);
		button.setText("取消");
		button.setBounds(260, 200, 90, 33);
				
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.setText("确认支付");
		button_1.setBounds(88, 200, 90, 33);
		
		final String []strArr=info.split(",");
		lblNewLabel.setText("当前需要支付："+strArr[10]+" 元");
		
		button_1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				MD5AndKillAction mD5AndKillAction=new MD5AndKillAction();
				DBHelper db=new DBHelper();
	     		String accountId=UiCollections.currentLoginUser.get("accountid");
			//	String accountId="111111";
				String sql="SELECT p_pwd FROM customers c WHERE c.accountid=?";
				List<Object> params=new ArrayList<Object>();
		        params.add(accountId);
		        String p_pwd1=db.find(sql, params).get("p_pwd");
		        String p_pwd2=mD5AndKillAction.KL(mD5AndKillAction.MD5(text.getText().trim()));
		        if(p_pwd1.equals(p_pwd2)){
		        	ticketOrderInfoDBH ticketorderInfoDBH=new ticketOrderInfoDBH();
		        	ticketorderInfoDBH.alter(strArr[0], "已付款");
		        	MessageDialog.openConfirm(shell, "成功提示", "密码正确，付款成功");
		        	int a=Order.t++;
					Order.text.setText(String.valueOf(a));
		    		shell.close();
		        }else{
		        	MessageDialog.openConfirm(shell, "失败提示", "密码错误，付款失败");
		        }
			}
		});
	}
}
