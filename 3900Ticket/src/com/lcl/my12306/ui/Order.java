package com.lcl.my12306.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import com.lcl.my12306.dao.ContactInfoDao;
import com.lcl.my12306.dao.CustomerInfoDBH;
import com.lcl.my12306.dao.DBHelper;
import com.lcl.my12306.dao.TraininfoDBH;
import com.lcl.my12306.dao.ticketOrderInfoDBH;
import com.lcl.my12306.utils.UiCollections;

import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.nebula.widgets.calendarcombo.CalendarCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

public class Order extends Composite {
	private Table table;
	static int t=0;
	static Text text;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Order(final Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackgroundImage(SWTResourceManager.getImage(Order.class, "/image/b11.jpg"));
		
		Label label = new Label(composite, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label.setBounds(98, 22, 97, 25);
		label.setText("查询订单日期");
		
		Label label_1 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(376, 30, 64, 2);
		
		Button button = new Button(composite, SWT.NONE);
		button.setBounds(658, 20, 80, 27);
		button.setText("查 询");
		
		final CalendarCombo calendarCombo = new CalendarCombo(composite, SWT.NONE);
		calendarCombo.setBounds(226, 20, 130, 25);
		
		final CalendarCombo calendarCombo_1 = new CalendarCombo(composite, SWT.NONE);
		calendarCombo_1.setBounds(458, 20, 130, 25);
		
		text = new Text(composite, SWT.BORDER);
		text.setVisible(false);
		text.setBounds(0, 0, 73, 23);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBackgroundMode(SWT.INHERIT_DEFAULT);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("订单号");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(82);
		tableColumn_1.setText("乘客");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(75);
		tableColumn_2.setText("出发地");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(75);
		tableColumn_3.setText("目的地");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(123);
		tableColumn_4.setText("订单日期");
		
		TableColumn tableColumn_8 = new TableColumn(table, SWT.NONE);
		tableColumn_8.setWidth(129);
		tableColumn_8.setText("订单到期时间");
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("乘车日期");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(80);
		tblclmnNewColumn_1.setText("车次");
		
		TableColumn tableColumn_9 = new TableColumn(table, SWT.NONE);
		tableColumn_9.setWidth(76);
		tableColumn_9.setText("座位类型");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(53);
		tableColumn_5.setText("座位号");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setWidth(69);
		tableColumn_6.setText("票价");
		
		TableColumn tableColumn_10 = new TableColumn(table, SWT.NONE);
		tableColumn_10.setWidth(100);
		tableColumn_10.setText("状态");
		
		TableColumn tableColumn_7 = new TableColumn(table, SWT.NONE);
		tableColumn_7.setWidth(100);
		tableColumn_7.setText("备注");
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.setText("支付");
		
		MenuItem menuItem_2 = new MenuItem(menu, SWT.NONE);
		menuItem_2.setText("学生票支付");
		
		//学生票支付
		
		
		new MenuItem(menu, SWT.SEPARATOR);
		
		MenuItem menuItem_1 = new MenuItem(menu, SWT.NONE);
		menuItem_1.setText("退票");
		sashForm.setWeights(new int[] {70, 407});
		
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				ticketOrderInfoDBH ticketorderInfoDBH=new ticketOrderInfoDBH();
				List<Map<String,String>> list=new ArrayList<Map<String,String>>();
				table.removeAll();
				TableItem ti;
				String time1=calendarCombo.getDateAsString().trim();
				String time2=calendarCombo_1.getDateAsString().trim();
				String accountId=UiCollections.currentLoginUser.get("accountid");
				list=ticketorderInfoDBH.find(accountId, time1, time2);
				for(Map map:list){
					ti=new TableItem(table,SWT.NONE);
					ti.setText(new String[]{String.valueOf( map.get("ids")),String.valueOf(map.get("c_name")),
							String.valueOf(map.get("t_init")),String.valueOf( map.get("t_dest")),String.valueOf( map.get("datep")),String.valueOf(map.get("past")),
							String.valueOf(map.get("datet")),String.valueOf( map.get("t_id")),String.valueOf(map.get("seat_kind")),String.valueOf( map.get("seat_id")),String.valueOf( map.get("price")),
							String.valueOf( map.get("condition")),String.valueOf( map.get("note"))});
				}
			}
		});

		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ticketOrderInfoDBH ticketorderInfoDBH=new ticketOrderInfoDBH();
				String time1=calendarCombo.getDateAsString().trim();
				String time2=calendarCombo_1.getDateAsString().trim();
				String accountId=UiCollections.currentLoginUser.get("accountid");
			//	String accountId="111111";
				List<Map<String,String>> list=new ArrayList<Map<String,String>>();
				table.removeAll();
				TableItem ti;
				list=ticketorderInfoDBH.find(accountId, time1, time2);
				for(Map map:list){
					ti=new TableItem(table,SWT.NONE);
					ti.setText(new String[]{String.valueOf( map.get("ids")),String.valueOf(map.get("c_name")),String.valueOf(map.get("t_init")),
							String.valueOf( map.get("t_dest")),String.valueOf( map.get("datep")),String.valueOf(map.get("past")),
							String.valueOf(map.get("datet")),String.valueOf( map.get("t_id")),String.valueOf(map.get("seat_kind")),
							String.valueOf( map.get("seat_id")),String.valueOf( map.get("price")),String.valueOf( map.get("condition")),String.valueOf( map.get("note"))});
				}
			}
		});
		//退票
		menuItem_1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				TraininfoDBH traininfoDBH=new TraininfoDBH();
				Refund refund= new Refund();
				TableItem[] tis=table.getSelection();
				String info ="";
				if(tis!=null&&tis.length>0){
					for(int i=0;i<12;i++){
						info+=tis[0].getText(i)+",";
					}
//					info=info.substring(0,info.length()-1);
			//		info=info+dates;
				//	System.out.println(info);
				}else{
					MessageDialog.openError(parent.getShell(), "温馨提示", "请选中您要操作的数据。。");
				}
				refund.open(info);
			}
		});
		//支付
		menuItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				TraininfoDBH traininfoDBH=new TraininfoDBH();
				Pay pay= new Pay();
				TableItem[] tis=table.getSelection();
				String info ="";
				if(tis!=null&&tis.length>0){
					for(int i=0;i<12;i++){
						info+=tis[0].getText(i)+",";
					}
				}else{
					MessageDialog.openError(parent.getShell(), "温馨提示", "请选中您要操作的数据。。");
				}
				pay.open(info);
			}
		});
		menuItem_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String str="";
				TableItem[] tis=table.getSelection();
				String accountid=UiCollections.currentLoginUser.get("accountid");
				ContactInfoDao contactInfoDao=new ContactInfoDao();
				CustomerInfoDBH customerInfoDBH=new CustomerInfoDBH();
				Map<String,String> map=customerInfoDBH.find(accountid);
				String name=map.get("cname");
				if(name.equals(tis[0].getText(1))){
					str=map.get("isstudent");	
				}else{
					DBHelper db=new DBHelper();
					String sq="select * from linkman where accountId=? and L_name=?";
					List<Object> par=new ArrayList<Object>();
					par.add(accountid.trim());
					par.add(tis[0].getText(1).trim());
					str=db.find(sq, par).get("isstudent");
				}
				if(str.equals("是")){
					TraininfoDBH traininfoDBH=new TraininfoDBH();
					Pay pay= new Pay();
			//		TableItem[] tis=table.getSelection();
					String info ="";
					if(tis!=null&&tis.length>0){
						for(int i=0;i<12;i++){
							if(i==10){
								info+=String.valueOf(Integer.parseInt(tis[0].getText(i))*0.5)+",";
							}
							info+=tis[0].getText(i)+",";
						}
					}else{
						MessageDialog.openError(parent.getShell(), "温馨提示", "请选中您要操作的数据。。");
					}
					pay.open(info);
				}else{
					MessageDialog.openError(parent.getShell(), "温馨提示", "对不起，您不是学生，不能购买学生票");
				}
			}
		});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
