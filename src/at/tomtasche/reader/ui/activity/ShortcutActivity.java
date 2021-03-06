package at.tomtasche.reader.ui.activity;

import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import at.tomtasche.reader.R;
import at.tomtasche.reader.background.DocumentLoader;
import at.tomtasche.reader.ui.widget.DocumentChooserDialogFragment;

public class ShortcutActivity extends FragmentActivity implements
		DocumentLoadingActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		transaction.add(new DocumentChooserDialogFragment(), "chooser");
		transaction.commit();
	}

	@Override
	public DocumentLoader loadUri(Uri uri) {
		ShortcutIconResource icon = Intent.ShortcutIconResource.fromContext(
				this, R.drawable.icon);

		Intent intent = new Intent();

		Intent launchIntent = new Intent(this, MainActivity.class);
		launchIntent.setAction(Intent.ACTION_VIEW);
		launchIntent.setData(uri);

		intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, launchIntent);
		intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, uri.getLastPathSegment());
		intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);

		setResult(RESULT_OK, intent);

		finish();

		return null;
	}
}
