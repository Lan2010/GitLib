//
//  XLConst.m
//  starChain
//
//  Created by rlx on 2018/6/6.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import <UIKit/UIKit.h>


CGFloat const xl_bottomButtonH = 45;

NSString * const adImgUrl = @"adImgUrl";
NSString * const adVersion = @"adVersion";
NSString * const adActionUrl = @"adActionUrl";

NSString * const login = @"login";
NSString * const deviceID = @"deviceID";
NSString * const phone = @"phone";
NSString * const token = @"token";
NSString * const versions = @"versions";
NSString * const isAddAddress = @"isAddAddress";
NSString * const homeGuideView  = @"homeGuideView";

NSString * const bundleVersion = @"bundle_Version";
NSString * const newAdImage = @"newAdImage";
NSString * const firstOpen = @"firstOpen";
NSString * const updataUrlStr = @"https://itunes.apple.com/us/app/qian-he-zi-gao-shou-yi-geng/id1156051367?mt=8&uo=4";

NSString * const tokenErrornNedLoginNotification = @"tokenErrornNedLoginNotification";
NSString * const adHienCompleteNotification = @"adHienCompleteNotification";
NSString * const resultErrrorNotification = @"resultErrrorNotification";
NSString * const cancellationNotification = @"cancellationNotification";
NSString * const loginSucceedNotification = @"loginSucceedNotification";
NSString * const acceptTaskNotification = @"acceptTaskNotification";

NSString * const registeredSucceedNotification = @"userRegisteredSucceedNotification";
NSString * const shareSueeccedNotification  = @"shareSueeccedNotification";



#define FORMALENVIROMENT 0

#if FORMALENVIROMENT

NSString * const baseUrlString = @"http://47.106.21.73/";

#else

NSString * const baseUrlString = @"http://192.168.11.17/";

#endif

NSString * const encryptionKey = @"AGAO53D4E5FY27H8I9J0G1I3";
NSString * const publickey = @"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDCf7uqE6U47EdsghaPO4CnXHf+Fy+339irPNEQgxADbDsfvMlU0R0JePvuglQk4nY1hyXoGoJ6pHyBBM5GNqAxs2qAWq+SeQfbY7INir5UfkAxEHoSm5IicECxQEpveT+Q/Z6zqxmpz7QICevon0evl9TacjgL2Mi52GdncgqB3wIDAQAB";
NSString * const privateKey = @"MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMJ/u6oTpTjsR2yCFo87gKdcd/4XL7ff2Ks80RCDEANsOx+8yVTRHQl4++6CVCTidjWHJegagnqkfIEEzkY2oDGzaoBar5J5B9tjsg2KvlR+QDEQehKbkiJwQLFASm95P5D9nrOrGanPtAgJ6+ifR6+X1NpyOAvYyLnYZ2dyCoHfAgMBAAECgYBsANIM12vf+BSRRIuSbdA7HzXieq2cxUhjjhnpuV2jCO6r6QFDf/tHz07WWriu4rIRd0KtaEscH0qjui9w/dRlzMJtLzh3NbuZCPVMwAGa/7hn5xhC2pb1xLnbTRrZ5cylp9JzNjDvQ7DOu0Wqv+Jw7dt2nWryD191NPpubo9FQQJBAOqS4dWvC2DW0usNYKDz0/VxeEHICOu+F3a4UpohZ3FKelCgFxPoF5QZpayeKAhdNWKQiS8M9LrQ8G+g4Dite2UCQQDUQ8SpxFLFOIbHcQe0nyX6ET4vbtSZMUo0AZaYReh0owOCrJiXKhq54tnPWC1jcWv31rdltfvAJRzytfWErU3zAkEAvGP+z/jncHzXpEN4KYvp3SOma15WG87tVUGvxW2ygEBKf9oy7p/l3+HBNyNGD4GcUG64XntPSYJot55CtgJOKQJAW6PeVgTiTy82UQilcP98n9et50CMHI9BH+7Hm3oi/6gZLeNiBXEyFKqxqmjBP7uKB3f1ub21sKDVPJv+IYfIrQJAKf7cdPtLcJ19rL7Pz+eyWs8RqLm3KWCYOHUpOodLdKiDHYWjR6l1KV+/fSQHP5TQF78/nzu1dawblDU8VSxYpw==";




