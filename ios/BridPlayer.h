//  Created by react-native-create-bridge

// import UIKit so you can subclass off UIView
#import <UIKit/UIKit.h>
#import <BridSDK/BridSDK.h>
#import "React/RCTViewManager.h"
#import "React/RCTComponent.h"

@class RCTEventDispatcher;

typedef NS_ENUM(NSUInteger, TypePlayer) {
    SinglePlayer,
    PlaylistPlayer
};

@interface BridPlayer : UIView

@property(nonatomic) NSDictionary *_Nonnull bridPlayerConfig;
@property(nonatomic) NSNumber *_Nonnull playerID;
@property(nonatomic) NSNumber *_Nonnull mediaID;
@property(nonatomic) TypePlayer type;
@property(nonatomic) NSString *_Nonnull typeOfPlayer;
@property(nonatomic) NSString *_Nonnull playerReference;
@property(nonatomic) BOOL useVPAIDSupport;
@property(nonatomic) BOOL controlAutoplay;
@property(nonatomic) BOOL scrollOnAd;
@property(nonatomic) NSNumber *_Nonnull setCornerRadius;
@property(nonatomic, strong) BVPlayer *_Nullable player;
@property(nonatomic) NSString *_Nonnull localization;
@property(nonatomic) NSNumber *_Nonnull setSeekSeconds;
@property(nonatomic) NSNumber *_Nonnull setSeekPreview;
@property(nonatomic) NSNumber *_Nonnull setSubtitleBottomOffset;

- (void)loadVideo:(NSNumber *_Nonnull)playerID mediaID:(NSNumber *_Nonnull)mediaID;
- (void)loadPlaylist:(NSNumber *_Nonnull)playerID mediaID:(NSNumber *_Nonnull)mediaID;
- (void)setMute:(BOOL)mute;
- (BOOL)isMuted;
- (BOOL)isAdPlaying;
- (BOOL)isPaused;
- (BOOL)isRepeated;
- (BOOL)isAutoplay;
- (NSNumber *_Nonnull)getPlayerCurrentTime;
- (NSNumber *_Nonnull)getVideoDuration;
- (NSNumber *_Nonnull)getAdDuration;
- (NSNumber *_Nonnull)getAdCurrentTime;
- (void)seekToTime:(float)time;
- (void)releasePlayer;
- (void)destroy;

@end
