package framework.alchemymodel;

public interface IListElementObject {

	public abstract void elementListPick(String elementZone,
			String elementLocator, boolean click);

	public abstract void elementListSelect(String elementLocator, int index,
			boolean click);

	public abstract boolean elementListVisible(String elementLocator, int index);

}