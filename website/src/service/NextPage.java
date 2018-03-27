package service;

public class NextPage {
	// 이동하려는 페이지 이름
	private String pageName;
	// 페이지 이동방식
	private boolean isRedirect;// true면 리다이렉트

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

	@Override
	public String toString() {
		return "Nextpage [pageName=" + pageName + ", isRedirect=" + isRedirect + "]";
	}
}
