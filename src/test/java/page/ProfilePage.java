package page;

public class ProfilePage extends AbstractPage {

    private static final String PROFILE_PAGE = "profile";

    public static String getUrl() {
        return getFullUrl(PROFILE_PAGE);
    }
}
