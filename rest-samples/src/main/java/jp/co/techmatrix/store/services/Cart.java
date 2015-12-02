package jp.co.techmatrix.store.services;

import java.util.List;

import jp.co.techmatrix.store.model.Book;

/**
 * 
 * @author kosugi
 *
 */
public interface Cart{
	/**
	 * 本をタイトルで検索します。
	 * @param title 検索したい本のタイトル
	 * @return titleで検索した本のリストを返却します。
	 */
	public List<Book> getItemByTitle(String title);
	
	/**
	 * 本をidで検索します。
	 * @param id 本のid
	 * @return idで検索した本を返却します。
	 */
	public Book getItemById(int id);
	
	/**
	 * 引数の本を追加します。
	 * @param book 追加したい本の内容が記載されたインスタンス
	 */
	public void addNewBook(Book book);
	
	/**
	 * idで指定した本を削除します。
	 * @param id 削除したい本のidを指定します。
	 */
	public void deleteBook(int id);
}
