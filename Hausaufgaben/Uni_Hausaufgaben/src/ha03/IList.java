/**
 * 
 */
package ha03;

/**
 * @author Param
 */

public interface IList {

	public void insertAt(int pos, Integer element);

	public void removeAt(int pos);

	public Integer getAt(int pos);

	public int search(Integer element);

	public void clear();

	public int getCount();
}
