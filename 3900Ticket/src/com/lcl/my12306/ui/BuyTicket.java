package com.lcl.my12306.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import com.lcl.my12306.dao.DBHelper;
import com.lcl.my12306.dao.StationinfoDBH;
import com.lcl.my12306.dao.TrainDBH;
import com.lcl.my12306.dao.TraininfoDBH;
import com.lcl.my12306.utils.UiCollections;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.nebula.widgets.calendarcombo.CalendarCombo;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

public class BuyTicket {

	protected Shell shell;
	private Display display;
	private Table table;
	private String dates;
	private List<String> t_ID=new ArrayList<String>();
    private List<String> soft_price=new ArrayList<String>();
    private List<String> hard_price=new ArrayList<String>();
    private List<String> seat_price=new ArrayList<String>();
    private List<String> start_time=new ArrayList<String>();
    private List<String> end_time=new ArrayList<String>();
	static Text txtD;
    static int t=0;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BuyTicket window = new BuyTicket();
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
		shell.setImage(SWTResourceManager.getImage(BuyTicket.class, "/image/logo.jpg"));
		shell.setSize(662, 449);
		shell.setText("购票中心");
		//shell.setText("购票中心      当前用户："+UiCollections.currentLoginUser.get("c_ID"));
		//全屏显示
		shell.setBounds(0, 0, display.getClientArea().width,display.getClientArea(). height);
		
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(shell, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackgroundImage(SWTResourceManager.getImage(BuyTicket.class, "/image/b11.jpg"));
		
		Label label = new Label(composite, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		label.setBounds(41, 29, 61, 27);
		label.setText("出发地");
		
		final Combo combo = new Combo(composite, SWT.NONE);
		combo.setBounds(118, 30, 88, 25);
		
		Label label_1 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(236, 42, 64, 2);
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		label_2.setBounds(333, 29, 61, 25);
		label_2.setText("目的地");
		
		final Combo combo_1 = new Combo(composite, SWT.NONE);
		combo_1.setBounds(398, 29, 88, 25);
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		label_3.setBounds(579, 29, 69, 27);
		label_3.setText("出发日期");
		
		Button button = new Button(composite, SWT.NONE);

		button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		button.setBounds(873, 29, 80, 27);
		button.setText("查询");
		
		final CalendarCombo calendarCombo = new CalendarCombo(composite, SWT.NONE);
		calendarCombo.setBounds(669, 29, 147, 25);
		
		txtD = new Text(composite, SWT.BORDER);
		txtD.setVisible(false);
		txtD.setBounds(0, 0, 73, 23);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(112);
		tableColumn.setText("车次");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(149);
		tableColumn_1.setText("出发站");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(149);
		tableColumn_2.setText("目的站");
		
		TableColumn tableColumn_9 = new TableColumn(table, SWT.NONE);
		tableColumn_9.setWidth(150);
		tableColumn_9.setText("出发日期");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(150);
		tableColumn_3.setText("出发时间");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(150);
		tableColumn_4.setText("到达时间");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(149);
		tableColumn_5.setText("软卧余票及票价");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setWidth(150);
		tableColumn_6.setText("硬卧余票及票价");
		
		TableColumn tableColumn_7 = new TableColumn(table, SWT.NONE);
		tableColumn_7.setWidth(150);
		tableColumn_7.setText("硬座余票及票价");
		
		TableColumn tableColumn_8 = new TableColumn(table, SWT.NONE);
		tableColumn_8.setWidth(150);
		tableColumn_8.setText("备注");
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.setText("购买");
		sashForm.setWeights(new int[] {86, 601});

		DBHelper db=new DBHelper();
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		String sql="select s_name from station";
		list=db.finds(sql, null);
	    for(int i=0;i<list.size();i++){
	    	combo.add(list.get(i).get("s_name"));
	    	combo_1.add(list.get(i).get("s_name"));
	    }

		txtD.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				table.removeAll();
				TableItem ti;
				TraininfoDBH traininfoDBH=new TraininfoDBH();
				TrainDBH trainDBH=new TrainDBH();
				ti=new TableItem(table,SWT.NONE);
				dates=calendarCombo.getDateAsString().trim();
				String t_init=combo.getText().trim();
			    String t_dest=combo_1.getText().trim();
			    List<Map<String,String>> list=new ArrayList<Map<String,String>>();
				List<Map<String,String>> list1=new ArrayList<Map<String,String>>();
				list=traininfoDBH.find(t_init, t_dest);
				list1=trainDBH.find(dates);
				for(int i=0,len=t_ID.size();i<len;i++){
				    double soft_seat=traininfoDBH.count(dates,t_ID.get(i), t_init, t_dest, "软卧");
				    double hard_seat=traininfoDBH.count(dates,t_ID.get(i), t_init, t_dest, "硬卧");
				    double t_seat=traininfoDBH.count(dates,t_ID.get(i), t_init, t_dest, "硬座");
					ti.setText(new String[]{String.valueOf(t_ID.get(i)),String.valueOf(t_init),
						String.valueOf(t_dest),String.valueOf(dates),String.valueOf(start_time.get(i)),String.valueOf(end_time.get(i)),
						String.valueOf((int)soft_seat)+"--"+String.valueOf(soft_price.get(i)),
						String.valueOf((int)hard_seat)+"--"+String.valueOf(hard_price.get(i)),
						String.valueOf((int)t_seat)+"--"+String.valueOf(seat_price.get(i))});
				}

			}
		});
		
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				DBHelper db=new DBHelper();
				int count=0;
				t_ID.clear();
				soft_price.clear();
				hard_price.clear();
				seat_price.clear();
				start_time.clear();
				end_time.clear();
				dates=calendarCombo.getDateAsString().trim();
			    String t_init=combo.getText().trim();
			    String t_dest=combo_1.getText().trim();
			    String sq="select IDs from ticketOrder  WHERE condition='未付款' AND PAST BETWEEN '2016-08-30 00:00:00'AND to_char(SYSDATE,'yyyy-mm-dd HH24:Mi:SS')";
			    List<Map<String,String>> past=db.finds(sq, null);
			    if(past!=null&&past.size()>0){
			    	System.out.println(past.size());
			    	for(int i=0,t=past.size();i<t;i++){
			    		System.out.println(past.get(i));
			    		String sq2="update ticketOrder set condition='已退票' where IDs=?";
			    		List<Object> params=new ArrayList<Object>();
			    		params.add(past.get(i).get("ids"));
			    		db.update(sq2, params);
			    	}
			    }
				if(dates!=null && t_init!=null && t_dest!=null && !"".equals(dates) && !"".equals(t_init) && !"".equals(t_dest)){
					table.removeAll();
					TableItem ti;
					TraininfoDBH traininfoDBH=new TraininfoDBH();
					TrainDBH trainDBH=new TrainDBH();
					List<Map<String,String>> list=new ArrayList<Map<String,String>>();
					List<Map<String,String>> list1=new ArrayList<Map<String,String>>();
					list=traininfoDBH.find(t_init, t_dest);
					list1=trainDBH.find(dates);
					for(Map<String, String> map:list){
						for(Map<String, String> map1:list1){
							if(((String) map.get("t_id")).trim().equals(((String) map1.get("t_id")).trim())){
								System.out.println(((String) map.get("t_id")).trim());
								System.out.println("--------------------");
								String sql="select * from t_daily_report where t_ID=? and ?=start_dates  or (? between start_dates and end_dates and end_dates!='无')";
								List<Object>params=new ArrayList<Object>();
								params.add(((String) map.get("t_id")).trim());
								params.add(dates);
								params.add(dates);
								if(db.finds(sql, params).size()>0){
									System.out.println(db.finds(sql, params).size());
									System.out.println(db.finds(sql, params));
									System.out.println(((String) map.get("t_id")).trim());
									System.out.println(dates);
									break;
								}
								t_ID.add(((String) map.get("t_id")).trim());
								soft_price.add(map.get("sum(ttt.soft_price)").trim());
								hard_price.add(map.get("sum(ttt.hard_price)").trim());
								seat_price.add(map.get("sum(ttt.seat_price)").trim());
								start_time.add(map.get("start_time"));
								end_time.add(map.get("end_time"));
								count++;
								ti=new TableItem(table,SWT.NONE);
								double soft_seat=traininfoDBH.count(dates,(String)map.get("t_id"), t_init, t_dest, "软卧");
								double hard_seat=traininfoDBH.count(dates,(String)map.get("t_id"), t_init, t_dest, "硬卧");
								double t_seat=traininfoDBH.count(dates,(String)map.get("t_id"), t_init, t_dest, "硬座");
									ti.setText(new String[]{String.valueOf( map.get("t_id")),String.valueOf(t_init),
											String.valueOf(t_dest),String.valueOf(dates),String.valueOf( map.get("start_time")),String.valueOf( map.get("end_time")),
											String.valueOf((int)soft_seat)+"--"+String.valueOf(map.get("sum(ttt.soft_price)")),
											String.valueOf((int)hard_seat)+"--"+String.valueOf(map.get("sum(ttt.hard_price)")),
											String.valueOf((int)t_seat)+"--"+String.valueOf(map.get("sum(ttt.seat_price)"))});
							}
						}
					}
					if(count<=0){
						ti=new TableItem(table,SWT.NONE);
						ti.setText(new String[]{"无","无","无","无","无","无","无"});
					}
				}else{
					MessageDialog.openError(shell, "失败提示", "信息太少，无法查询");
				}
			}
		});

		menuItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
//				TraininfoDBH traininfoDBH=new TraininfoDBH();
				Buy buy= new Buy();
				TableItem[] tis=table.getSelection();
				String info ="";
				if(tis!=null&&tis.length>0){
					for(int i=0;i<9;i++){
						info+=tis[0].getText(i)+",";
					}
//					info=info.substring(0,info.length()-1);
					info=info+dates;
				//	System.out.println(info);
				}else{
					MessageDialog.openError(shell, "温馨提示", "请选中您要购买的数据。。");
				}
				buy.open(info);
			}
		});
	}
}
