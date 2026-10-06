package com.bridtvsdkmodule;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.facebook.react.ReactActivity;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.NoSuchKeyException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UnexpectedNativeTypeException;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.ThemedReactContext;

import tv.brid.sdk.api.BridPlayer;
import tv.brid.sdk.api.BridPlayerBuilder;
import tv.brid.sdk.player.listeners.BridPlayerListener;
import tv.brid.sdk.player.PlayerEvents;

class RNBridPlayerView extends FrameLayout implements LifecycleEventListener, BridPlayerListener {

  private BridPlayer bridPlayer;
  private Context mContext;
  private BridPlayerBuilder bridPlayerBuilder;

  private ThemedReactContext mThemedReactContext;

  public String playerReferenceString = null;
  private FrameLayout playerHolder;

  public RNBridPlayerView(@NonNull Context context) {
    super(context);
    mContext = context;
    init(context);
  }

  public RNBridPlayerView(@NonNull ThemedReactContext context, ReactApplicationContext reactApplicationContext) {
    super(getNonBuggyContext(context, reactApplicationContext));
    mThemedReactContext = context;
    reactApplicationContext.addLifecycleEventListener(this);
    ProcessLifecycleOwner.get().getLifecycle().addObserver(appLifecycleObserver);
    init(context.getReactApplicationContext().getCurrentActivity());
  }

  public RNBridPlayerView(@NonNull Context context, View rootView) {
    super(context);
    mContext = context;
    init(context);
  }

  public RNBridPlayerView(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public RNBridPlayerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public RNBridPlayerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  private void init(Context context) {

    this.setFocusable(true);
    this.setFocusableInTouchMode(true);

    playerHolder = (FrameLayout) LayoutInflater.from(context).inflate(R.layout.brid_player_view_holder, null);

    this.addView(playerHolder);

  }

  public BridPlayer getBridPlayer() {
    return bridPlayer;
  }

  public void loadVideo(int playerId, int videoId, boolean vpaidSupport, boolean isFullscreen, boolean controlAutoplay,
      boolean enableAdControls, String creditsLabelColor, String playerReference, int borderRadius, String language,
      int seekSeconds, int seekPreview, int controllerTimeoutMs, int ccBottomOffset) {
    bridPlayerBuilder = new BridPlayerBuilder(getContext(), this);
    bridPlayerBuilder.useVpaidSupport(vpaidSupport);
    bridPlayerBuilder.enableAutoplay(!controlAutoplay);
    bridPlayerBuilder.setCreditsLabelColor(creditsLabelColor);
    bridPlayerBuilder.setCornerRadius(borderRadius);
    bridPlayerBuilder.setPlayerLanguage(language);
    bridPlayerBuilder.setSeekSeconds(seekSeconds);
    bridPlayerBuilder.setSeekPreviewEnabled(seekPreview);
    bridPlayerBuilder.setControllerTimeoutMs(controllerTimeoutMs);
    bridPlayerBuilder.setCaptionsBottomOffset(ccBottomOffset);

    if (playerReference != null)
      bridPlayerBuilder.setPlayerReference(playerReference);
    bridPlayer = bridPlayerBuilder.build();
    bridPlayer.setBridListener(this);
    bridPlayer.loadVideo(playerId, videoId);

  }

  public void loadVideo(int playerId, int videoId) {
    // Reuse the already-configured player (built from bridPlayerConfig) so its
    // styling — corner radius, credits color, language, seek, etc. — is kept.
    // Rebuilding a bare BridPlayerBuilder here is what dropped the corner radius
    // when loading a new video via the command. Mirrors loadPlaylist(int,int).
    if (bridPlayer != null) {
      bridPlayer.loadVideo(playerId, videoId);
      return;
    }
    bridPlayerBuilder = new BridPlayerBuilder(getContext(), this);
    bridPlayer = bridPlayerBuilder.build();
    if (bridPlayer != null) {
      bridPlayer.loadVideo(playerId, videoId);
    }
  }

  public void loadPlaylist(int playerId, int playlistId) {
    if (bridPlayer != null) {
      bridPlayer.loadPlaylist(playerId, playlistId);
    }
  }

  public void loadPlaylist(int playerId, int playlistId, boolean vpaidSupport, boolean isFullscreen,
      boolean controlAutoplay, boolean enableAdControls, String creditsLabelColor, String playerReference,
      int borderRadius, String language, int seekSeconds, int seekPreview, int controllerTimeoutMs,
      int ccBottomOffset) {
    bridPlayerBuilder = new BridPlayerBuilder(getContext(), playerHolder);
    bridPlayerBuilder.useVpaidSupport(vpaidSupport);
    bridPlayerBuilder.fullscreen(isFullscreen);
    bridPlayerBuilder.enableAutoplay(!controlAutoplay);
    bridPlayerBuilder.setCreditsLabelColor(creditsLabelColor);
    bridPlayerBuilder.setCornerRadius(borderRadius);
    bridPlayerBuilder.setPlayerLanguage(language);
    bridPlayerBuilder.setSeekSeconds(seekSeconds);
    bridPlayerBuilder.setSeekPreviewEnabled(seekPreview);
    bridPlayerBuilder.setControllerTimeoutMs(controllerTimeoutMs);
    bridPlayerBuilder.setCaptionsBottomOffset(ccBottomOffset);
    if (playerReference != null)
      bridPlayerBuilder.setPlayerReference(playerReference);
    bridPlayer = bridPlayerBuilder.build();
    bridPlayer.setBridListener(this);
    bridPlayer.loadPlaylist(playerId, playlistId);
  }

  public void play() {
    if (bridPlayer != null)
      bridPlayer.play();
  }

  public void pause() {
    if (bridPlayer != null)
      bridPlayer.pause();
  }

  public void destroyPlayer() {
    if (bridPlayer != null)
      bridPlayer.release();
  }

  public void setFullScreen(boolean isOn) {
    if (bridPlayer != null)
      bridPlayer.setFullScreen(isOn);
  }

  public long getCurrentTime() {
    return bridPlayer.getCurrentPosition();
  }

  public void toastMessage(String message) {
    Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
  }

  public void unMute() {
    if (bridPlayer != null)
      bridPlayer.setMute(false);
  }

  public void mute() {
    if (bridPlayer != null)
      bridPlayer.setMute(true);
  }

  public void seekToTime(int seekToTime) {
    if (bridPlayer != null)
      bridPlayer.seekToPosition((long) (seekToTime * 1000));
  }

  public void showControls() {
    if (bridPlayer != null)
      bridPlayer.showControls();
  }

  public void showPoster() {
    if (bridPlayer != null)
      bridPlayer.showPoster();
  }

  public void hidePoster() {
    if (bridPlayer != null)
      bridPlayer.hidePoster();
  }

  public void hideControls() {
    if (bridPlayer != null)
      bridPlayer.hideControls();
  }

  public void previous() {
    if (bridPlayer != null)
      bridPlayer.previous();
  }

  public void next() {
    if (bridPlayer != null)
      bridPlayer.next();
  }

  private static Context getNonBuggyContext(ThemedReactContext reactContext,
      ReactApplicationContext appContext) {
    Context superContext = reactContext;
    if (!contextHasBug(appContext.getCurrentActivity())) {
      superContext = appContext.getCurrentActivity();
    } else if (contextHasBug(superContext)) {
      // we have the bug! let's try to find a better context to use
      if (!contextHasBug(reactContext.getCurrentActivity())) {
        superContext = reactContext.getCurrentActivity();
      } else if (!contextHasBug(reactContext.getApplicationContext())) {
        superContext = reactContext.getApplicationContext();
      }
    }
    return superContext;
  }

  private static boolean contextHasBug(Context context) {
    return context == null ||
        context.getResources() == null ||
        context.getResources().getConfiguration() == null;
  }

  @Override
  public void requestLayout() {
    super.requestLayout();

    // The spinner relies on a measure + layout pass happening after it calls
    // requestLayout().
    // Without this, the widget never actually changes the selection and doesn't
    // call the
    // appropriate listeners. Since we override onLayout in our ViewGroups, a layout
    // pass never
    // happens after a call to requestLayout, so we simulate one here.
    post(measureAndLayout);
  }

  private final Runnable measureAndLayout = new Runnable() {
    @Override
    public void run() {
      measure(MeasureSpec.makeMeasureSpec(getWidth(), MeasureSpec.EXACTLY),
          MeasureSpec.makeMeasureSpec(getHeight(), MeasureSpec.EXACTLY));
      layout(getLeft(), getTop(), getRight(), getBottom());
    }
  };

  @Override
  public void onHostResume() {
    Log.d("Lifecycle react", "onHostResume");
    // play();
    showControls();
  }

  @Override
  public void onHostPause() {
    Log.d("Lifecycle react", "onHostPause");
    if (bridPlayer != null && bridPlayer.isFullscreen()) return;
    pause();
  }

  @Override
  public void onHostDestroy() {
    Log.d("Lifecycle react", "onHostDestroy");
    removeAppLifecycleObserver();
    destroyPlayer();
  }

  private final LifecycleEventObserver appLifecycleObserver = (owner, event) -> {
    if (event == Lifecycle.Event.ON_STOP) pause();
  };

  public void removeAppLifecycleObserver() {
    ProcessLifecycleOwner.get().getLifecycle().removeObserver(appLifecycleObserver);
  }

  @Override
  public void onEvent(String status, String playerReference) {
    Log.d("BridPlayerEvent", status);
    WritableMap event = Arguments.createMap();

    switch (status) {
      case PlayerEvents.EVENT_VIDEO_BUFFERING:
        event.putString("name", "VIDEO_BUFFERING");
        event.putString("playerReference", playerReference);
        sendEvent(mThemedReactContext, "BridPlayerEvents" + getId(), event);
        break;

      case PlayerEvents.EVENT_PLAYER_LOADED:
        event.putString("name", "VIDEO_LOADING");
        event.putString("playerReference", playerReference);
        sendEvent(mThemedReactContext, "BridPlayerEvents" + getId(), event);
        break;
      case "STARTED":
        event.putString("name", "VIDEO_INITIALIZED");
        event.putString("playerReference", playerReference);
        sendEvent(mThemedReactContext, "BridPlayerEvents" + getId(), event);
        break;
      case PlayerEvents.EVENT_VIDEO_PLAY:
        event.putString("name", "VIDEO_PLAYING");
        event.putString("playerReference", playerReference);
        sendEvent(mThemedReactContext, "BridPlayerEvents" + getId(), event);
        break;
      case PlayerEvents.EVENT_VIDEO_PAUSE:
        event.putString("name", "VIDEO_PAUSED");
        event.putString("playerReference", playerReference);
        sendEvent(mThemedReactContext, "BridPlayerEvents" + getId(), event);
        break;
      case PlayerEvents.EVENT_VIDEO_END:
        event.putString("name", "VIDEO_STOPPED");
        event.putString("playerReference", playerReference);
        sendEvent(mThemedReactContext, "BridPlayerEvents" + getId(), event);
        break;
      case PlayerEvents.EVENT_VIDEO_SEEK:
        event.putString("name", "VIDEO_SEEK");
        event.putString("playerReference", playerReference);
        sendEvent(mThemedReactContext, "BridPlayerEvents" + getId(), event);
        break;
      case PlayerEvents.EVENT_VIDEO_AUTOPLAY:
        event.putString("name", "VIDEO_AUTOPLAY");
        event.putString("playerReference", playerReference);
        sendEvent(mThemedReactContext, "BridPlayerEvents" + getId(), event);
        break;
      case PlayerEvents.EVENT_AD_LOADED:
        event.putString("name", "AD_LOADED");
        event.putString("playerReference", playerReference);
        sendEvent(mThemedReactContext, "BridPlayerEvents" + getId(), event);
        break;
      case PlayerEvents.EVENT_AD_COMPLETED:
        event.putString("name", "AD_COMPLETED");
        event.putString("playerReference", playerReference);
        sendEvent(mThemedReactContext, "BridPlayerEvents" + getId(), event);
        break;
      case PlayerEvents.EVENT_AD_RESUMED:
        event.putString("name", "AD_RESUMED");
        event.putString("playerReference", playerReference);
        sendEvent(mThemedReactContext, "BridPlayerEvents" + getId(), event);
        break;
      case PlayerEvents.EVENT_AD_SKIPPED:
        event.putString("name", "AD_SKIPPED");
        event.putString("playerReference", playerReference);
        sendEvent(mThemedReactContext, "BridPlayerEvents" + getId(), event);
        break;
      case PlayerEvents.EVENT_AD_STARTED:
        event.putString("name", "AD_STARTED");
        event.putString("playerReference", playerReference);
        sendEvent(mThemedReactContext, "BridPlayerEvents" + getId(), event);
        break;
      case PlayerEvents.EVENT_AD_PAUSED:
        event.putString("name", "AD_PAUSED");
        event.putString("playerReference", playerReference);
        sendEvent(mThemedReactContext, "BridPlayerEvents" + getId(), event);
        break;
      case PlayerEvents.EVENT_AD_TAPPED:
        event.putString("name", "AD_TAPPED");
        event.putString("playerReference", playerReference);
        sendEvent(mThemedReactContext, "BridPlayerEvents" + getId(), event);
        break;
      case PlayerEvents.EVENT_ALL_ADS_COMPLETED:
        event.putString("name", "AD_COMPLETED");
        event.putString("playerRef", playerReference);
        sendEvent(mThemedReactContext, "BridPlayerEvents" + getId(), event);
        break;
      case PlayerEvents.EVENT_AD_PROGRESS:
        event.putString("name", "AD_INPROGRESS");
        event.putString("playerReference", playerReference);
        sendEvent(mThemedReactContext, "BridPlayerEvents" + getId(), event);
        break;
      case PlayerEvents.EVENT_AD_CLICKED:
        event.putString("name", "AD_CLICKED");
        event.putString("playerReference", playerReference);
        sendEvent(mThemedReactContext, "BridPlayerEvents" + getId(), event);
        break;

      case PlayerEvents.EVENT_FULLSCREEN_OPEN_REQUESTED:
        event.putString("name", "FULLSCREEN_OPEN");
        event.putString("playerReference", playerReference);
        sendEvent(mThemedReactContext, "BridPlayerEvents" + getId(), event);
        break;

      case PlayerEvents.EVENT_FULLSCREEN_CLOSE_REQUESTED:
        event.putString("name", "FULLSCREEN_CLOSE");
        event.putString("playerReference", playerReference);
        sendEvent(mThemedReactContext, "BridPlayerEvents" + getId(), event);
        break;

      // PLAYER ERROR EVENTS
      case PlayerEvents.EVENT_AD_ERROR:
      case PlayerEvents.EVENT_AD_BREAK_FETCH_ERROR:
        event.putString("name", "AD_ERROR");
        event.putString("playerReference", playerReference);
        sendEvent(mThemedReactContext, "BridPlayerEvents" + getId(), event);
        break;
      case PlayerEvents.EVENT_VIDEO_ERROR:
        event.putString("name", "unsupportedFormat");
        event.putString("playerReference", playerReference);
        sendEvent(mThemedReactContext, "BridPlayerEvents" + getId(), event);
        break;
      case PlayerEvents.EVENT_VIDEO_NETWORK_ERROR:
        event.putString("name", "lostIntenetConnection");
        event.putString("playerReference", playerReference);
        sendEvent(mThemedReactContext, "BridPlayerEvents" + getId(), event);
        break;
      case PlayerEvents.EVENT_VIDEO_CMS_ERROR:
        event.putString("name", "videoBadUrl");
        event.putString("playerReference", playerReference);
        sendEvent(mThemedReactContext, "BridPlayerEvents" + getId(), event);
        break;
      case PlayerEvents.EVENT_VIDEO_LIVESTREAM_ERROR:
        event.putString("name", "livestreamError");
        event.putString("playerReference", playerReference);
        sendEvent(mThemedReactContext, "BridPlayerEvents" + getId(), event);
        break;
      case PlayerEvents.EVENT_VIDEO_PROTECTED_ERROR:
        event.putString("name", "protectedContent");
        event.putString("playerReference", playerReference);
        sendEvent(mThemedReactContext, "BridPlayerEvents" + getId(), event);
        break;

    }
  }

  private void sendEvent(ReactContext reactContext,
      String eventName,
      @Nullable WritableMap params) {
    reactContext
        .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
        .emit(eventName, params);
  }

  public void setConfig(ReadableMap prop) {

    int playerId = 0, mediaId = 0, borderRadius = 1, seekSeconds = 0, seekPreview = 0, controllerTimeoutMs = 5000,
        ccBottomOffset = 0;
    boolean useVpaid = false, playlist = false, isFullscreen = false, controlAutoplay = false, enableAdControls = false;
    String creditsLabelColor = null, language = "en";

    try {
      if (prop.hasKey("playerID")) {
        playerId = prop.getInt("playerID");
      }
      if (prop.hasKey("mediaID")) {
        mediaId = prop.getInt("mediaID");
      }
      if (prop.hasKey("typeOfPlayer"))
        playlist = prop.getString("typeOfPlayer").equals("Playlist");

      if (prop.hasKey("useVPAIDSupport"))
        useVpaid = prop.getBoolean("useVPAIDSupport");
      if (prop.hasKey("setFullscreen"))
        isFullscreen = prop.getBoolean("setFullscreen");

      if (prop.hasKey("controlAutoplay"))
        controlAutoplay = prop.getBoolean("controlAutoplay");

      if (prop.hasKey("enableAdControls"))
        enableAdControls = prop.getBoolean("enableAdControls");

      if (prop.hasKey("creditsLabelColor"))
        creditsLabelColor = prop.getString("creditsLabelColor");

      if (prop.hasKey("playerReference"))
        playerReferenceString = prop.getString("playerReference");

      if (prop.hasKey("setCornerRadius"))
        if (prop.getInt("setCornerRadius") > 0)
          borderRadius = prop.getInt("setCornerRadius");

      if (prop.hasKey("localization"))
        language = prop.getString("localization");

      if (prop.hasKey("setSeekSeconds"))
        seekSeconds = prop.getInt("setSeekSeconds");

      if (prop.hasKey("setSeekPreview"))
        seekPreview = prop.getInt("setSeekPreview");

      if (prop.hasKey("setControllerTimeoutMS"))
        controllerTimeoutMs = prop.getInt("setControllerTimeoutMS");

      if (prop.hasKey("setCcBottomOffset"))
        ccBottomOffset = prop.getInt("setCcBottomOffset");

      if (playlist)
        loadPlaylist(playerId, mediaId, useVpaid, isFullscreen, controlAutoplay, enableAdControls, creditsLabelColor,
            playerReferenceString, borderRadius, language, seekSeconds, seekPreview, controllerTimeoutMs,
            ccBottomOffset);
      else
        loadVideo(playerId, mediaId, useVpaid, isFullscreen, controlAutoplay, enableAdControls, creditsLabelColor,
            playerReferenceString, borderRadius, language, seekSeconds, seekPreview, controllerTimeoutMs,
            ccBottomOffset);

    } catch (NumberFormatException e) {
      loadVideo(0, 0);
    } catch (NoSuchKeyException ekey) {
      loadVideo(0, 0);
    } catch (UnexpectedNativeTypeException ekey) {
      loadVideo(0, 0);
    }
  }
};
