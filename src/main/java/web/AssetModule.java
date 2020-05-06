package web;

import org.jooby.Jooby;
import org.jooby.Results;

/**
 *
 * @author meani898
 */
public class AssetModule extends Jooby {

	public AssetModule() {
		assets("/*.html");
		assets("/css/*.css");
		assets("/js/*.js");
		assets("/images/*.png");
		assets("/images/*.jpg");
// make index.html the default page
		assets("/", "index.html");
// prevent 404 errors due to browsers requesting favicons
		get("/favicon.ico", () -> Results.noContent());
	}
}
