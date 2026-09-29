# react-native-bridtv-sdk-module

BridTV SDK player for react native

## Installation

```sh
npm install react-native-bridtv-sdk-module
or 
yarn add react-native-bridtv-sdk-module


IOS 

After NPM, cd to IOS and run  pod install

If pod install reports that CocoaPods could not find compatible versions for pod "BridSDK" (e.g. after updating this package), run  pod update BridSDK  in the ios folder.

Android 

To update dependencies and fetch the latest packages open Android Studio and run Gradle Sync. By performing the Gradle sync, you ensure that your Android project is up to date with the latest packages and dependencies, which can help resolve compatibility issues and provide access to new features and bug fixes.


```


## Usage

```js
import { BridPlayer } from "react-native-bridtv-sdk-module";

// ...
const App = () => {
  const bridPlayerRef = React.useRef<BridPlayer>(null);

  const handleAllPlayerEvents = (eventData) => {
     console.log(eventData);
  };

  const handleVideoLoad = (eventData) => {cd
    console.log('VIDEO LOADED' + '-' + eventData.playerReference);
  };

          <BridPlayer
            ref={bridPlayerRef3}
            style={styles.square}
            bridPlayerConfig={{
              playerReference: 'player_3', // Player reference important for Events
              playerID: xxxx, // PlayerID from BridTV cms
              mediaID: xxxxxxxx, //VideoID or PlaylistID from BridTv cms
              typeOfPlayer: 'Single', // Single or Playlist
              controlAutoplay: false, //enables the client to take control over autoplay
              scrollOnAd: true, //This option enables scrolling during ad and is specific to the iOS platform. By default, Android has scrolling enabled during ads.
              creditsLabelColor: '614BC3', // To achieve color modification for credits label, it is necessary to provide a sequence of six hexadecimal characters, excluding the '#' symbol.
              setCornerRadius: 30, //This property enables setting the corner radius to the player itself. Its value is in pixels.
              localization: 'en', //This property allows selecting the language in which the player and IMA will operate.
              doubleTapSeek: 10, // This property set seek seconds for double tap seek player UI. -  from version 1.1.1
              setSeekPreview: 1, // When set to 1, the feature is enabled and will be visible in all operational modes of the player. When set to 2 the thumbnail image preview during seeking will be available exclusively when the player is in fullscreen mode. - from version 1.1.1
              setSeekSeconds:5,// The number of seconds to seek when user double tap on player view.
              setControllerTimeoutMS:5000,//Timeout for player controller in milliseconds
              setCcBottomOffset:16 //Change Closed Captions bottom offset - in pixels

            }}
            //Callback for Events from all players in one Activity {"message": "video/ad event", "playerReference": "reverence to player from props"}
            handleAllPlayerEvents={(eventData) =>handleAllPlayerEvents(eventData)}
            //Video
            handleVideoLoad={(eventData) => handleVideoLoad(eventData)}
            handleVideoStart={(eventData) => handleVideoStart(eventData)}
            //Error
            handleVideoError={(eventData) => handleVideoError(eventData)}
          />

```

##  Props

The BridPlayer component accepts the following props:

+ **style** (optional): Specifies the style for the player component.

+ **bridPlayerConfig**: An object that contains configuration options for the player.  It includes properties playerID, mediaID, typeOfPlayer, useVPAIDSupport,      playerReference, controlAutoplay, scrollOnAd, creditsLabelColor, setCornerRadius, localization. Explanations for each of the properties are given in the section above.

+ **localization - Supported languages:**:
  - en (English) - default
  - es (Spanish)
  - de (German)
  - fr (French)
  - it (Italian)
  - ru (Russian)
  - pt (Portuguese)
  - sr (Serbian)
  - me (Montenegrin)
  - hr (Croatian)
  - bs (Bosnian)
  - sl (Slovenian)

+ **handleAllPlayerEvents** : Callback for Events from all players in one Activity {"name": "video/ad event", "playerReference": "reverence to player from props"}
+ **Video event callbacks**: These props allow you to specify callback functions for various video events like:
    - handleVideoLoad, 
    -  handleVideoStart, 
    -  handleVideoPlay, 
    -  handleVideoBuffering, 
    -  handleVideoProgress, 
    -  handleVideoPaused,
    -  handleVideoEnd, 
    -  handleVideoSeek, 
    -  handleFullscreenOpen, 
    -  handleFullscreenClose,
    -  handleVideoAutoplay
      //Pip events
    -  handlePlayerEnterPip?: () => void;
    -  handlePlayerExitPip?: () => void;

+ **Ad event callbacks**: These props allow you to specify callback functions for various ad events like:
    -  handleVideoAdLoaded, 
    -  handleVideoAdCompleted, 
     - handleVideoAdResumed, 
     - handleVideoAdStart, 
     - handleVideoAdPaused, 
     - handleAdProgress, 
    -  handleVideoAdTapped, 
    -  handleVideoAdSkipped, 
    -  handleVideoAdEnd

+ **handleVideoError**: A callback function that handles video errors. It receives an errorEvent object. These are the error event types that can occur with the BridPlayer:

    - **adError**:
        - Name: adError
        - Message: Error occurred during ad playback.
        - Code: 300

    - **videoBadUrl**:
        - Name: playerVideoBadUrl
        - Message: Invalid video from BridTv CMS/Invalid video URL.
        - Code: 101

    - **unsupportedFormat**:
        - Name: playerUnsupportedFormat
        - Message: Video player error. Probably unsupported video format.
        - Code: 102

    - **protectedContent**:
        - Name: playerProtectedContent
        - Message: Cannot play protected content.
        - Code: 103

    - **lostIntenetConnection**:
        - Name: playerLostInternetConnection
        - Message: Lost internet connection.
        - Code: 100

    - **liveStreamError**:
        - Name: playerLivestreamError
        - Message: An error occurred during live stream playback.
        - Code: 200

These error types represent various issues that can occur during the playback of videos or ads with the BridPlayer. By handling these error events appropriately, you can provide better error feedback and take necessary actions to address the specific error conditions encountered during playback.

##  Methods

+ **play(): void:** Plays the video.
+ **pause(): void:** Pauses the video.
+ **previous(): void:** Plays the previous video in the playlist.
+ **next(): void:** Plays the next video in the playlist.
+ **mute(): void:** Mutes the video.
+ **unMute(): void:** Unmutes the video.
+ **loadVideo(playerID: number, mediaID: number): void:** Loads a video with the specified playerID and mediaID from BridTv CMS.
+ **loadPlaylist(playerID: number, mediaID: number): void**: Loads a playlist with the specified playerID and mediaID from BridTv CMS.
+ **setFullscreen(fullscreen: boolean):** void: Sets the fullscreen mode of the player. Pass true to enter fullscreen mode or false to exit fullscreen mode.
+ **showControls(): void:** Enable the video controls.
+ **hideControls(): void:** Disable the video controls.
+ **showPoster(): void:** Show video thumbnail.
+ **hidePoster(): void:** Hide video thumbnail.
+ **isPlayingAd(): Promise<bool>**Checks if an ad is currently playing.
+ **getPlayerCurrentTime():** Promise<number | null>: Retrieves the current playback time of the player in milliseconds. Returns a promise that resolves with the current time or null if the player is not loaded.
+ **getAdDuration(): Promise<number | null>:** Retrieves the duration of the currently playing ad in milliseconds. Returns a promise that resolves with the ad duration or null if no ad is playing.
+ **getVideoDuration(): Promise<number | null>:** Retrieves the duration of the currently loaded video in milliseconds. Returns a promise that resolves with the video duration or null if no video is loaded.
+ **isPaused(): Promise<bool>:** Checks if the video is currently paused.
+ **isRepeated(): Promise<bool>:** Checks if the video is already repeated.
+ **destroyPlayer(): void:** Destroys the native player instance and cleans up any resources associated with it.
+ **isAutoplay(): Promise<bool>:** Method is used to check if the current video is set to autoplay..


*Please note that these methods should be called on an instance of the BridPlayer class.*

***Take a look at our Example App for best practices while implementing BridPlayer RN module.***

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

---

Made by Brid.tv