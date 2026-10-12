#import "BridtvSdkModule.h"

@implementation BridtvSdkModule {
  BOOL hasListeners;
  NSString *lastEvent;
  NSString *lastEventAd;
  NSString *lastReference;
  NSString *lastReferenceAd;
}

RCT_EXPORT_MODULE(BridtvSdkModule);

- (instancetype)init {
  self = [super init];
  if (self) {
    [[NSNotificationCenter defaultCenter] addObserver:self
                                             selector:@selector(playerEventReceived:)
                                                 name:@"BridPlayer"
                                               object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self
                                             selector:@selector(playerEventReceived:)
                                                 name:@"BridPlayerAd"
                                               object:nil];
  }
  return self;
}

- (void)dealloc {
  [[NSNotificationCenter defaultCenter] removeObserver:self];
  lastEvent = nil;
  lastEventAd = nil;
  lastReference = nil;
  lastReferenceAd = nil;
}

+ (BOOL)requiresMainQueueSetup {
  return YES;
}

- (void)startObserving {
  hasListeners = YES;
}

- (void)stopObserving {
  hasListeners = NO;
}

- (NSArray<NSString *> *)supportedEvents {
  return @[@"BridPlayerEvents"];
}

- (void)playerEventReceived:(NSNotification *)notification {
  if (!hasListeners || !self.callableJSModules) return;

  if ([notification.name isEqualToString:@"BridPlayer"]) {
    id eventValue = notification.userInfo[@"event"];
    id referenceValue = notification.userInfo[@"reference"];

    if ((lastEvent != eventValue) || (lastReference != referenceValue)) {
      [self sendEventWithName:@"BridPlayerEvents"
                         body:@{@"name": eventValue ?: @"",
                                @"playerReference": referenceValue ?: @""}];
      lastEvent = eventValue;
      lastReference = referenceValue;
    }
  }

  if ([notification.name isEqualToString:@"BridPlayerAd"]) {
    id adValue = notification.userInfo[@"ad"];
    id referenceValue = notification.userInfo[@"reference"];

    if ((lastEventAd != adValue) || (lastReferenceAd != referenceValue)) {
      [self sendEventWithName:@"BridPlayerEvents"
                         body:@{@"name": adValue ?: @"",
                                @"playerReference": referenceValue ?: @""}];
      lastEventAd = adValue;
      lastReferenceAd = referenceValue;
    }
  }
}

@end
