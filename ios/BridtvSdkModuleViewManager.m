#import <React/RCTViewManager.h>
#import "BridPlayer.h"
#import <React/RCTUIManager.h>
#import <UIKit/UIKit.h>
#import <React/RCTLog.h>
#import <React/RCTEventEmitter.h>
#import "BridtvSdkModule.h"

@interface BridtvSdkModuleViewManager : RCTViewManager

@end

@implementation BridtvSdkModuleViewManager

RCT_EXPORT_MODULE(BridtvSdkModuleView)

- (UIView *)view
{
    return [[BridPlayer alloc] init];
}

RCT_EXPORT_VIEW_PROPERTY(bridPlayerConfig, NSDictionary);
RCT_EXPORT_VIEW_PROPERTY(playerID, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(mediaID, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(typeOfPlayer, NSString);
RCT_EXPORT_VIEW_PROPERTY(playerReference, NSString);
RCT_EXPORT_VIEW_PROPERTY(useVPAIDSupport, BOOL);
RCT_EXPORT_VIEW_PROPERTY(controlAutoplay, BOOL);
RCT_EXPORT_VIEW_PROPERTY(scrollOnAd, BOOL);
RCT_EXPORT_VIEW_PROPERTY(setCornerRadius, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(localization, NSString);
RCT_EXPORT_VIEW_PROPERTY(setSeekSecond, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(setSeekPreview, NSNumber);
RCT_EXPORT_VIEW_PROPERTY(setSubtitleBottomOffset, NSNumber);

RCT_EXPORT_METHOD(pause:(nonnull NSNumber *)reactTag) {
    [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, BridPlayer *> *viewRegistry) {
        BridPlayer *view = viewRegistry[reactTag];
        if (![view isKindOfClass:[BridPlayer class]] || view.player == nil) {
            RCTLogError(@"Invalid view returned from registry, expecting BridPlayer, got: %@", view);
        } else {
            [view.player pause];
        }
    }];
}

RCT_EXPORT_METHOD(play:(nonnull NSNumber *)reactTag) {
    [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, BridPlayer *> *viewRegistry) {
        BridPlayer *view = viewRegistry[reactTag];
        if (![view isKindOfClass:[BridPlayer class]] || view.player == nil) {
            RCTLogError(@"Invalid view returned from registry, expecting BridPlayer, got: %@", view);
        } else {
            [view.player play];
        }
    }];
}

RCT_EXPORT_METHOD(next:(nonnull NSNumber *)reactTag) {
    [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, BridPlayer *> *viewRegistry) {
        BridPlayer *view = viewRegistry[reactTag];
        if (![view isKindOfClass:[BridPlayer class]] || view.player == nil) {
            RCTLogError(@"Invalid view returned from registry, expecting BridPlayer, got: %@", view);
        } else {
            [view.player next];
        }
    }];
}

RCT_EXPORT_METHOD(previous:(nonnull NSNumber *)reactTag) {
    [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, BridPlayer *> *viewRegistry) {
        BridPlayer *view = viewRegistry[reactTag];
        if (![view isKindOfClass:[BridPlayer class]] || view.player == nil) {
            RCTLogError(@"Invalid view returned from registry, expecting BridPlayer, got: %@", view);
        } else {
            [view.player previous];
        }
    }];
}

RCT_EXPORT_METHOD(destroyPlayer:(nonnull NSNumber *)reactTag) {
    [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, BridPlayer *> *viewRegistry) {
        BridPlayer *view = viewRegistry[reactTag];
        if (![view isKindOfClass:[BridPlayer class]] || view.player == nil) {
            RCTLogError(@"Invalid view returned from registry, expecting BridPlayer, got: %@", view);
        } else {
          [view releasePlayer];
        }
    }];
}

RCT_EXPORT_METHOD(loadVideo:(nonnull NSNumber *)reactTag:(nonnull NSNumber *)playerID:(nonnull NSNumber *)mediaID) {
    [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, BridPlayer *> *viewRegistry) {
        BridPlayer *view = viewRegistry[reactTag];
        if (![view isKindOfClass:[BridPlayer class]] || view.player == nil) {
            RCTLogError(@"Invalid view returned from registry, expecting BridPlayer, got: %@", view);
        } else {
            [view loadVideo:playerID mediaID:mediaID];
        }
    }];
}

RCT_EXPORT_METHOD(loadPlaylist:(nonnull NSNumber *)reactTag:(nonnull NSNumber *)playerID:(nonnull NSNumber *)mediaID) {
    [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, BridPlayer *> *viewRegistry) {
        BridPlayer *view = viewRegistry[reactTag];
        if (![view isKindOfClass:[BridPlayer class]] || view.player == nil) {
            RCTLogError(@"Invalid view returned from registry, expecting BridPlayer, got: %@", view);
        } else {
            [view loadPlaylist:playerID mediaID:mediaID];
        }
    }];
}

RCT_EXPORT_METHOD(showControls:(nonnull NSNumber *)reactTag) {
    [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, BridPlayer *> *viewRegistry) {
        BridPlayer *view = viewRegistry[reactTag];
        if (![view isKindOfClass:[BridPlayer class]] || view.player == nil) {
            RCTLogError(@"Invalid view returned from registry, expecting BridPlayer, got: %@", view);
        } else {
            [view.player showControls];
        }
    }];
}

RCT_EXPORT_METHOD(hideControls:(nonnull NSNumber *)reactTag) {
    [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, BridPlayer *> *viewRegistry) {
        BridPlayer *view = viewRegistry[reactTag];
        if (![view isKindOfClass:[BridPlayer class]] || view.player == nil) {
            RCTLogError(@"Invalid view returned from registry, expecting BridPlayer, got: %@", view);
        } else {
            [view.player hideControls];
        }
    }];
}

RCT_EXPORT_METHOD(showPoster:(nonnull NSNumber *)reactTag) {
    [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, BridPlayer *> *viewRegistry) {
        BridPlayer *view = viewRegistry[reactTag];
        if (![view isKindOfClass:[BridPlayer class]] || view.player == nil) {
            RCTLogError(@"Invalid view returned from registry, expecting BridPlayer, got: %@", view);
        } else {
            [view.player showPoster];
        }
    }];
}

RCT_EXPORT_METHOD(hidePoster:(nonnull NSNumber *)reactTag) {
    [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, BridPlayer *> *viewRegistry) {
        BridPlayer *view = viewRegistry[reactTag];
        if (![view isKindOfClass:[BridPlayer class]] || view.player == nil) {
            RCTLogError(@"Invalid view returned from registry, expecting BridPlayer, got: %@", view);
        } else {
            [view.player hidePoster];
        }
    }];
}

RCT_EXPORT_METHOD(mute:(nonnull NSNumber *)reactTag) {
    [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, BridPlayer *> *viewRegistry) {
        BridPlayer *view = viewRegistry[reactTag];
        if (![view isKindOfClass:[BridPlayer class]] || view.player == nil) {
            RCTLogError(@"Invalid view returned from registry, expecting BridPlayer, got: %@", view);
        } else {
            [view.player mute];
        }
    }];
}

RCT_EXPORT_METHOD(unMute:(nonnull NSNumber *)reactTag) {
    [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, BridPlayer *> *viewRegistry) {
        BridPlayer *view = viewRegistry[reactTag];
        if (![view isKindOfClass:[BridPlayer class]] || view.player == nil) {
            RCTLogError(@"Invalid view returned from registry, expecting BridPlayer, got: %@", view);
        } else {
            [view.player unmute];
        }
    }];
}

RCT_EXPORT_METHOD(setFullscreen:(nonnull NSNumber *)reactTag:(BOOL)fullscreen) {
    [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, BridPlayer *> *viewRegistry) {
        BridPlayer *view = viewRegistry[reactTag];
        if (![view isKindOfClass:[BridPlayer class]] || view.player == nil) {
            RCTLogError(@"Invalid view returned from registry, expecting BridPlayer, got: %@", view);
        } else {
            if (fullscreen)
                [view.player setFullscreenON];
            else
                [view.player setFullscreenOFF];
        }
    }];
}

RCT_EXPORT_METHOD(seekToTime:(nonnull NSNumber *)reactTag:(float)time) {
    [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, BridPlayer *> *viewRegistry) {
        BridPlayer *view = viewRegistry[reactTag];
        if (![view isKindOfClass:[BridPlayer class]] || view.player == nil) {
            RCTLogError(@"Invalid view returned from registry, expecting BridPlayer, got: %@", view);
        } else {
            [view.player seekToTime:time];
        }
    }];
}

RCT_REMAP_METHOD(getCurrentTime, getCurrentTimeForTag:(nonnull NSNumber *)reactTag
                 resolver:(RCTPromiseResolveBlock)resolve
                 rejecter:(RCTPromiseRejectBlock)reject) {
    [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
        BridPlayer *view = viewRegistry[reactTag];
        
        if (![view isKindOfClass:[BridPlayer class]] || !view.player) {
            reject(@"E_NO_PLAYER", @"Player instance not found", nil);
            return;
        }
        
        NSNumber *time = [view getPlayerCurrentTime];
        resolve(time ?: @0);
    }];
}

RCT_REMAP_METHOD(getVideoDuration, getVideoDurationForTag:(nonnull NSNumber *)reactTag
                 resolver:(RCTPromiseResolveBlock)resolve
                 rejecter:(RCTPromiseRejectBlock)reject) {
    [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
        BridPlayer *view = viewRegistry[reactTag];
        
        if (![view isKindOfClass:[BridPlayer class]] || !view.player) {
            reject(@"E_NO_PLAYER", @"Player instance not found", nil);
            return;
        }
        
        NSNumber *videoDuration = [view getVideoDuration];
        resolve(videoDuration ?: @0);
    }];
}

RCT_REMAP_METHOD(getAdDuration, getAdDurationForTag:(nonnull NSNumber *)reactTag
                 resolver:(RCTPromiseResolveBlock)resolve
                 rejecter:(RCTPromiseRejectBlock)reject) {
    [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
        BridPlayer *view = viewRegistry[reactTag];
        
        if (![view isKindOfClass:[BridPlayer class]] || !view.player) {
            reject(@"E_NO_PLAYER", @"Player instance not found", nil);
            return;
        }
        
        NSNumber *time = [view getAdDuration];
        resolve(time ?: @0);
    }];
}

RCT_REMAP_METHOD(getAdCurrentTime, getAdCurrentTimeForTag:(nonnull NSNumber *)reactTag
                 resolver:(RCTPromiseResolveBlock)resolve
                 rejecter:(RCTPromiseRejectBlock)reject) {
    [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
        BridPlayer *view = viewRegistry[reactTag];
        
        if (![view isKindOfClass:[BridPlayer class]] || !view.player) {
            reject(@"E_NO_PLAYER", @"Player instance not found", nil);
            return;
        }
        
        NSNumber *time = [view getAdCurrentTime];
        resolve(time ?: @0);
    }];
}

RCT_REMAP_METHOD(isMuted, isMutedForTag:(nonnull NSNumber *)reactTag
                 resolver:(RCTPromiseResolveBlock)resolve
                 rejecter:(RCTPromiseRejectBlock)reject) {
    [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
        BridPlayer *view = viewRegistry[reactTag];
        
        if (![view isKindOfClass:[BridPlayer class]]) {
            reject(@"E_NO_PLAYER", @"Player instance not found", nil);
            return;
        }
        
        NSNumber *isMuted = [view isMuted] ? @1 : @0;
        resolve(isMuted);
    }];
}

RCT_REMAP_METHOD(isPlayingAd, isPlayingAdForTag:(nonnull NSNumber *)reactTag
                 resolver:(RCTPromiseResolveBlock)resolve
                 rejecter:(RCTPromiseRejectBlock)reject) {
    [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
        BridPlayer *view = viewRegistry[reactTag];
        
        if (![view isKindOfClass:[BridPlayer class]]) {
            reject(@"E_NO_PLAYER", @"Player instance not found", nil);
            return;
        }
        
        NSNumber *isAdPlaying = [view isAdPlaying] ? @1 : @0;
        resolve(isAdPlaying);
    }];
}

RCT_REMAP_METHOD(isPaused, isPausedForTag:(nonnull NSNumber *)reactTag
                 resolver:(RCTPromiseResolveBlock)resolve
                 rejecter:(RCTPromiseRejectBlock)reject) {
    [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
        BridPlayer *view = viewRegistry[reactTag];
        
        if (![view isKindOfClass:[BridPlayer class]]) {
            reject(@"E_NO_PLAYER", @"Player instance not found", nil);
            return;
        }
        
        NSNumber *isPaused = [view isPaused] ? @1 : @0;
        resolve(isPaused);
    }];
}

RCT_REMAP_METHOD(isRepeated, isRepeatedForTag:(nonnull NSNumber *)reactTag
                 resolver:(RCTPromiseResolveBlock)resolve
                 rejecter:(RCTPromiseRejectBlock)reject) {
    [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
        BridPlayer *view = viewRegistry[reactTag];
        
        if (![view isKindOfClass:[BridPlayer class]]) {
            reject(@"E_NO_PLAYER", @"Player instance not found", nil);
            return;
        }
        
        NSNumber *isRepeated = [view isRepeated] ? @1 : @0;
        resolve(isRepeated);
    }];
}

RCT_REMAP_METHOD(isAutoplay, isAutoplayForTag:(nonnull NSNumber *)reactTag
                 resolver:(RCTPromiseResolveBlock)resolve
                 rejecter:(RCTPromiseRejectBlock)reject) {
    [self.bridge.uiManager addUIBlock:^(__unused RCTUIManager *uiManager, NSDictionary<NSNumber *, UIView *> *viewRegistry) {
        BridPlayer *view = viewRegistry[reactTag];
        
        if (![view isKindOfClass:[BridPlayer class]]) {
            reject(@"E_NO_PLAYER", @"Player instance not found", nil);
            return;
        }
        
        NSNumber *isAutoplay = [view isAutoplay] ? @1 : @0;
        resolve(isAutoplay);
    }];
}

@end