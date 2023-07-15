package pageIUs.nopcommerce;

public class BasePageUI {
	//không tham số = truyền cố định tên page
	public static final String SHOPPING_CART_PAGE_LINK ="//a[text()='Shopping cart']";
	public static final String ABOUT_US_PAGE_LINK ="//a[text()='About us']";
	public static final String NEWS_PAGE_LINK ="//div[@class='footer']//a[text()='News']";
	public static final String SITE_MAP_LINK="//a[text()='Sitemap']";
	public static final String HOME_PAGE_IMG="//img[@alt='nopCommerce demo store']";
	// có 1 tham số= dymamic page name
	public static final String FOOTER_PAGE_LINK_BY_NAME="//div[@class='footer']//a[text()='%s']";
}
