package com.lcl.my12306.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import com.lcl.my12306.dao.ContactInfoDao;
import com.lcl.my12306.dao.CustomerInfoDBH;
import com.lcl.my12306.utils.UiCollections;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.custom.TableCursor;

public class ContactInfo extends Composite {
	private static Table table;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ContactInfo(final Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);

		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));

		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(170);
		tblclmnNewColumn_1.setText("姓名");

		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(226);
		tblclmnNewColumn_3.setText("身份证号码");

		TableCursor tableCursor = new TableCursor(table, SWT.NONE);

		Menu menu = new Menu(tableCursor);
		tableCursor.setMenu(menu);

		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		
		menuItem.setText("删除");
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(183);
		tableColumn.setText("是否是学生");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(213);
		tableColumn_1.setText("学校名称");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(198);
		tableColumn_2.setText("学号");
		sashForm.setWeights(new int[] {352});

		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//获取要删除的那条记录
				TableItem[] tis=table.getSelection(); //说明用户可能选中了多行
				if(tis!=null){
					String l_name="";
					for(TableItem ti:tis){//每循环一次就是一行数据
						//取出当前行中第一列的值
						l_name+=ti.getText(0)+",";					
					}
					//去掉最后面的那个逗号
					l_name=l_name.substring(0,l_name.lastIndexOf(","));

					//发送到数据库中，执行数据删除
					ContactInfoDao contactInfoDao=new ContactInfoDao();
					if(contactInfoDao.del(l_name)>0){
						showInfo();
					}else{
						MessageDialog.openError(parent.getShell(), "失败提示", "联系人删除失败!!!");
					}
				}else{
					MessageDialog.openError(parent.getShell(), "温馨提示", "请选中您要删除的联系人");
				}
			}
		});
	}
				

			/**
			 * 显示联系人信息
			 */
			public static void showInfo(){
				table.removeAll();

				ContactInfoDao contactInfoDao= new ContactInfoDao();
				List<Map<String, String>> list=contactInfoDao.find(UiCollections.currentLoginUser.get("accountid"));
				if(list!=null && list.size()>0){
					TableItem ti;
					for(Map<String,String> map:list){
						ti=new TableItem(table,SWT.NONE);
						ti.setText(new String[]{map.get("l_name"),map.get("l_id"),map.get("isstudent"),map.get("school"),map.get("stu_id")});
					}
				}
			}


			@Override
			protected void checkSubclass() {
				// Disable the check that prevents subclassing of SWT components
			}
		}
