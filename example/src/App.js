import * as React from 'react';
import { Text } from 'react-native';
import {
  StyleSheet,
  View,
  Button,
  ScrollView,
  SafeAreaView,
  TextInput,
} from 'react-native';

import BridPlayer from 'react-native-bridtv-sdk-module';

const App = () => {
  const bridPlayerRef = React.useRef(null);
  const bridPlayerRef2 = React.useRef(null);
  // const bridPlayerRef3 = React.useRef(null);
  const [playerId, setPlayerId] = React.useState('50057');
  const [videoId, setVideoId] = React.useState('2676826');
  const [playlistId, setPlaylistId] = React.useState('21751');

  // const [video1, setVideo1] = React.useState(false);
  // const [video2, setVideo2] = React.useState(false);
  // const [video3, setVideo3] = React.useState(false);

  // React.useEffect(() => {
  //   setTimeout(() => {
  //     setVideo2(true);
  //   }, 5000);

  //   setTimeout(() => {
  //     setVideo3(true);
  //   }, 10000);
  // }, []);

  // const handleAllPlayerEvents = (eventData) => {
  //   console.log(eventData);
  // };

  // const handleVideoLoad = (eventData) => {
  //   console.log('VIDEO LOADED' + '-' + eventData.playerReference);
  // };
  // const handleVideoPlay = (eventData) => {
  //   console.log('VIDEO PLAYED' + '-' + eventData.playerReference);
  // };

  // const handleVideoBuffering = (eventData) => {
  //   console.log('VIDEO BUFFERING' + '-' + eventData.playerReference);
  // };

  // const handleVideoStart = (eventData) => {
  //   console.log('VIDEO STARTED' + '-' + eventData.playerReference);
  // };

  // const handleVideoPause = (eventData) => {
  //   console.log('VIDEO PAUSED' + '-' + eventData.playerReference);
  // };
  // const handleVideoProgress = (eventData) => {
  //   console.log('VIDEO PROGRESS' + '-' + eventData.playerReference);
  // };

  // const handleVideoSeek = (eventData) => {
  //   console.log('VIDEO SEEK' + '-' + eventData.playerReference);
  // };

  // const handleVideoEnd = (eventData) => {
  //   console.log('VIDEO END' + '-' + eventData.playerReference);
  // };

  // const handleFullscreenOpen = (eventData) => {
  //   console.log('FULL SCREEN OPEN' + '-' + eventData.playerReference);
  // };

  // const handleFullscreenClose = (eventData) => {
  //   console.log('FULL SCREEN CLOSE' + '-' + eventData.playerReference);
  // };

  // // Ad Events
  // const handleVideoAdLoaded = (eventData) => {
  //   console.log('AD LOADED' + '-' + eventData.playerReference);
  // };
  // const handleVideoAdCompleted = (eventData) => {
  //   console.log('AD COMPLETED' + '-' + eventData.playerReference);
  // };
  // const handleVideoAdResumed = (eventData) => {
  //   console.log('AD RESUMED' + '-' + eventData.playerReference);
  // };
  // const handleVideoAdStart = (eventData) => {
  //   console.log('AD STARTED' + '-' + eventData.playerReference);
  // };
  // const handleVideoAdPaused = (eventData) => {
  //   console.log('AD PAUSED' + '-' + eventData.playerReference);
  // };

  // const handleVideoAdProgress = (eventData) => {
  //   console.log('AD PROGRESS' + '-' + eventData.playerReference);
  // };

  // const handleVideoAdTapped = (eventData) => {
  //   console.log('AD TAPPED' + '-' + eventData.playerReference);
  // };

  // const handleVideoAdSkipped = (eventData) => {
  //   console.log('AD SKIPPED' + '-' + eventData.playerReference);
  // };

  // const handleVideoAutoplay = (eventData) => {
  //   console.log('VIDEO AUTOPLAY' + '-' + eventData.playerReference);
  // };

  // //VIDEO ERROR EVENTS
  // const handleVideoError = (errorEvent) => {
  //   console.log(
  //     'Error Code => ' +
  //       errorEvent.code +
  //       ' | Error Name => ' +
  //       errorEvent.name +
  //       ' | Error Message => ' +
  //       errorEvent.message
  //   );
  // };

  return (
    <SafeAreaView>
      <ScrollView contentContainerStyle={styles.scrollViewContainer}>
        <View style={[styles.container]}>
          <BridPlayer
            ref={bridPlayerRef}
            style={styles.square}
            bridPlayerConfig={{
              playerReference: 'player_1',
              playerID: 50057, // PlayerID from BridTV cms
              mediaID: 2676826, //VideoID or PlaylistID from BridTv cms
              typeOfPlayer: 'Single', //Single or Playlist
              controlAutoplay: false, //enables the client to take control over autoplay
              scrollOnAd: true, //This option enables scrolling during ad and is specific to the iOS platform. By default, Android has scrolling enabled during ads.
              creditsLabelColor: '614BC3', // To achieve color modification for credits label, it is necessary to provide a sequence of six hexadecimal characters, excluding the '#' symbol.
              setCornerRadius: 30, //This property enables setting the corner radius to the player itself. Its value is in pixels.
              doubleTapSeek: 10, // This property set seek seconds for double tap seek player UI.
              setSeekPreview: 1, // When set to 1, the feature is enabled and will be visible in all operational modes of the player. When set to 2 the thumbnail image preview during seeking will be available exclusively when the player is in fullscreen mode.
              setSeekSeconds: 5,
            }}
          />

          {/* <View style={styles.buttonContainer} />
          {video2 && (
            <BridPlayer
              ref={bridPlayerRef2}
              style={styles.square}
              bridPlayerConfig={{
                playerReference: 'player_2',
                playerID: 41519, // PlayerID from BridTV cms
                mediaID: 1497964, //VideoID or PlaylistID from BridTv cms
                typeOfPlayer: 'Single', //Single or Playlist
                controlAutoplay: false, //enables the client to take control over autoplay
                scrollOnAd: true, //This option enables scrolling during ad and is specific to the iOS platform. By default, Android has scrolling enabled during ads.
                creditsLabelColor: '614BC3', // To achieve color modification for credits label, it is necessary to provide a sequence of six hexadecimal characters, excluding the '#' symbol.
              }}
              //Callback for Events from all players in one Activity {"message": "video/ad event", "playerReference": "reverence to player from props"}
            />
          )} */}
          {/* <View style={styles.buttonContainer} />
          {video3 && (
            <BridPlayer
              ref={bridPlayerRef3}
              style={styles.square}
              bridPlayerConfig={{
                playerReference: 'player_3',
                playerID: 41519, // PlayerID from BridTV cms
                mediaID: 1497964, //VideoID or PlaylistID from BridTv cms
                typeOfPlayer: 'Single', // Single or Playlist
              }}
              //Callback for Events from all players in one Activity {"message": "video/ad event", "playerReference": "reverence to player from props"}
              handleAllPlayerEvents={(eventData) =>
                handleAllPlayerEvents(eventData)
              }
            />
          )} */}

          <View style={styles.buttonContainer}>
            <Button
              title="Prev"
              onPress={() => bridPlayerRef.current?.isAutoplay()}
            />

            <Button
              title="Play"
              onPress={() => bridPlayerRef.current?.play()}
            />

            <Button
              title=" Player 2Pause"
              onPress={() => bridPlayerRef2.current?.pause()}
            />

            <Button
              title="Next"
              onPress={() => bridPlayerRef.current?.next()}
            />
          </View>

          <View style={styles.buttonContainer}>
            <Button
              title="SHOW POSTER"
              onPress={() => bridPlayerRef.current?.showPoster()}
            />

            <Button
              title="HIDE POSTER"
              onPress={() => bridPlayerRef.current?.hidePoster()}
            />

            <Button
              title="MUTE"
              onPress={() => bridPlayerRef.current?.mute()}
            />

            <Button
              title="UNMUTE"
              onPress={() => bridPlayerRef.current?.unMute()}
            />
          </View>

          <View style={styles.buttonContainer}>
            <Button
              title="set Fullscreen"
              onPress={() => bridPlayerRef.current?.setFullscreen(true)}
            />

            <Button
              title="seek To Time"
              onPress={() => bridPlayerRef.current?.seekToTime(30)}
            />

            <Button
              title="Current Time"
              onPress={() => bridPlayerRef.current?.getPlayerCurrentTime()}
            />
          </View>

          <View style={styles.buttonContainer}>
            <Button
              title="destroy Player"
              onPress={() => bridPlayerRef.current?.destroyPlayer()}
            />

            <Button
              title="load Video"
              onPress={() =>
                bridPlayerRef.current?.loadVideo(
                  parseInt(playerId, 10),
                  parseInt(videoId, 10)
                )
              }
            />

            <Button
              title="load Playlist"
              onPress={() =>
                bridPlayerRef.current?.loadPlaylist(
                  parseInt(playerId, 10),
                  parseInt(playlistId, 10)
                )
              }
            />
          </View>

          <View style={styles.buttonContainer}>
            <Button
              title="Hide Controls"
              onPress={() => bridPlayerRef.current?.hideControls()}
            />

            <Button
              title="Show Controls"
              onPress={() => bridPlayerRef.current?.showControls()}
            />

            <Button
              title="Video Duration"
              onPress={() => bridPlayerRef.current?.getVideoDuration()}
            />
          </View>
          <View style={styles.buttonContainer}>
            <Button
              title="Is Paused"
              onPress={async () => {
                const paused = await bridPlayerRef.current?.isPaused();
                console.log(paused);

                return paused;
              }}
            />

            <Button
              title="Is Repeated"
              onPress={async () => {
                const repeated = await bridPlayerRef.current?.isRepeated();
                console.log(repeated);

                return repeated;
              }}
            />
          </View>
        </View>
        <View>
          <Text>Player ID:</Text>
          <TextInput
            label="Player ID"
            placeholder="Enter a number"
            keyboardType="numeric"
            value={playerId}
            onChangeText={(text) => setPlayerId(text)}
          />
          <Text>Video ID:</Text>
          <TextInput
            label="Video ID"
            placeholder="Enter a number"
            keyboardType="numeric"
            value={videoId}
            onChangeText={(text) => setVideoId(text)}
          />
          <Text>Playlist ID:</Text>
          <TextInput
            label="Playlist ID"
            placeholder="Enter a number"
            keyboardType="numeric"
            value={playlistId}
            onChangeText={(text) => setPlaylistId(text)}
          />
        </View>
      </ScrollView>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  scrollViewContainer: {
    justifyContent: 'center',
  },

  backgroundVideo: {
    position: 'absolute',
    top: 0,
    left: 0,
    bottom: 0,
    right: 0,
  },

  container: {
    flex: 1,
    alignItems: 'center',
    paddingTop: 50,
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
  subContainer: {
    position: 'absolute',
    top: 100,
    left: 50,
    flex: 1,
  },
  square: {
    width: 320,
    aspectRatio: 16 / 9,
  },
  buttonContainer: {
    borderRadius: 10,
    width: '100%',
    height: 'auto',
    flexDirection: 'row',
    justifyContent: 'space-between',
    padding: 10,
    marginTop: 20,
    shadowColor: '#000000',
    shadowOffset: {
      width: 0,
      height: 3,
    },
  },
});

export default App;
