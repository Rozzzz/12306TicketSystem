package com.lcl.my12306.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.lcl.my12306.dao.DBHelper;
import com.lcl.my12306.dao.TraininfoDBH;
import com.lcl.my12306.dao.ticketOrderInfoDBH;
import com.lcl.my12306.utils.UiCollections;

import org.eclipse.swt.widgets.Composite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

public class Buy {

	protected Shell shell;
	private Text text;
	private static String info;
    private String seat_kind;
    private String price;
    private String name9;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Buy window = new Buy();
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
		shell.setImage(SWTResourceManager.getImage(Buy.class, "/image/12306_logo.jpg"));
		shell.setSize(565, 378);
		shell.setText("购票窗口");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackgroundImage(SWTResourceManager.getImage(Buy.class, "/image/ticket.jpg"));
		
		final Combo combo = new Combo(composite, SWT.NONE);
		combo.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		combo.setBounds(122, 140, 147, 25);
		
		Label label = new Label(composite, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label.setText(" 购票人:");
		label.setBounds(52, 139, 64, 34);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setText(" 身份证:");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_1.setBounds(286, 142, 64, 26);
		
		text = new Text(composite, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		text.setEnabled(false);
		text.setBounds(356, 140, 179, 25);
		
		final Combo combo_1 = new Combo(composite, SWT.NONE);
		combo_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		combo_1.setBounds(122, 174, 147, 25);
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setText(" 座位类型：");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_2.setBounds(36, 174, 79, 25);
		
		final Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(425, 181, 90, 30);
		btnNewButton.setText("购  买");
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.BOLD));
		lblNewLabel.setBounds(240, 55, 91, 34);
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.NORMAL));
		label_4.setBounds(74, 53, 105, 25);
		
		Label label_5 = new Label(composite, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.NORMAL));
		label_5.setBounds(388, 53, 98, 25);
		
		Label label_7 = new Label(composite, SWT.NONE);
		label_7.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_7.setBounds(240, 105, 105, 25);
		
		Label label_8 = new Label(composite, SWT.NONE);
		label_8.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_8.setBounds(74, 84, 88, 25);
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
	    label_3.setBounds(398, 84, 79, 25);

		final String []strArr=info.split(",");
		lblNewLabel.setText(strArr[0]);
		label_4.setText(strArr[1]);
		label_5.setText(strArr[2]);
		label_7.setText(strArr[3]);
		label_8.setText(strArr[4]);
		label_3.setText(strArr[5]);
		
	    combo_1.add("软卧"+strArr[6]);
	    combo_1.add("硬卧"+strArr[7]);
	    combo_1.add("硬座"+strArr[8]);
	    
	    final Label label_9 = new Label(composite, SWT.NONE);
	    label_9.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
	    label_9.setBounds(122, 216, 111, 17);

	    DBHelper db=new DBHelper();
	    String accountId=UiCollections.currentLoginUser.get("accountid");
	    String sq="select L_name from linkman where accountid=?";
	    List<Object>par=new ArrayList<Object>();
	    par.add(accountId);
	    List<Map<String,String>> name=db.finds(sq, par);
	    for(int i=0,t=name.size();i<t;i++){
	    	combo.add(name.get(i).get("l_name"));
	    }
	    String sq2="select cname from customers where accountId=?";
	    name9=db.find(sq2, par).get("cname").trim();
	    combo.add(db.find(sq2, par).get("cname"));
	    
	    
	    
		combo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				DBHelper db=new DBHelper();
				String name=combo.getText().trim();
				if(name.equals(name9)){
					String sq9="select c_ID from customers where accountId=?";
				    List<Object>par9=new ArrayList<Object>();
				    par9.add(UiCollections.currentLoginUser.get("accountid"));
					text.setText(db.find(sq9, par9).get("c_id"));
				}else{
					String sq9="select L_ID from linkman where accountId=? and L_name=?";
					List<Object>par9=new ArrayList<Object>();
				    par9.add(UiCollections.currentLoginUser.get("accountid"));
				    par9.add(name);
				   if(db.find(sq9, par9)!=null&&db.find(sq9, par9).size()>0){
					   text.setText(db.find(sq9, par9).get("l_id"));
				   }else{
					   text.setText("");
				   }
				}
			}
		});
	    
		combo_1.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				seat_kind=combo_1.getText().trim();
				String []strArr1=seat_kind.split("--");
				String count=strArr1[0].substring(2, strArr1[0].length());
				if("0".equals(count.trim())){
					label_9.setText(" 余票不足");
					btnNewButton.setEnabled(false);
				}else{
					price=strArr1[1];
					label_9.setText(" ");
					btnNewButton.setEnabled(true);
				}
			}
		});
	    
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				DBHelper db=new DBHelper();
				ticketOrderInfoDBH ticketorderInfoDBH=new ticketOrderInfoDBH();
				TraininfoDBH traininfoDBH=new TraininfoDBH();
				String sql="select times from train where t_ID=?";
		        List<Object> params=new ArrayList<Object>();
                params.add(strArr[0]);
                int time=(int)db.getTotal(sql, params);
				String name=combo.getText().trim();
				String c_ID=text.getText().trim();
				if(name==null||c_ID==null||"".equals(name.trim())||"".equals(c_ID)){
					MessageDialog.openError(shell, "失败提示", "请完善乘车人信息。。");
				}
				seat_kind=seat_kind.substring(0,2);
				String accountId=UiCollections.currentLoginUser.get("accountid");
			//	String accountId="111111";c
				String dates=strArr[9];
				if(ticketorderInfoDBH.add(accountId, name, c_ID, dates, strArr[1], strArr[2], seat_kind, strArr[0], time,price)>0/*&&traininfoDBH.buy_tickets(strArr[0],  strArr[1], strArr[2], seat_kind)>0*/){
					MessageDialog.openInformation(shell, "成功提示", "订票成功，请尽快付款。。");
					BuyTicket buyTicket=new BuyTicket();
					int a=buyTicket.t++;
					buyTicket.txtD.setText(String.valueOf(a));
					shell.close();
				}else{
					MessageDialog.openError(shell, "失败提示", "订票失败。。");
				}
			}
		});
	}
}
//System.out.println(accountId);
//System.out.println(name);
//System.out.println(c_ID);
//System.out.println(dates);
//System.out.println(strArr[1]);
//System.out.println(strArr[2]);
//System.out.println(seat_kind);
//System.out.println(strArr[0]);
//System.out.println(time);