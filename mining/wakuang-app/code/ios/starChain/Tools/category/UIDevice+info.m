//
//  UIDevice+info.m
//  starChain
//
//  Created by rlx on 2018/6/20.
//  Copyright © 2018年 rlx. All rights reserved.
//

#import "UIDevice+info.h"
//获取mac
#include <sys/sysctl.h>
#include <sys/socket.h>
#include <net/if.h>
#include <net/if_dl.h>

#import <sys/sockio.h>
#import <sys/ioctl.h>
#import <arpa/inet.h>

#import <CoreTelephony/CTCarrier.h>
#import <CoreTelephony/CTTelephonyNetworkInfo.h>

#import "sys/utsname.h"

@implementation UIDevice (info)

+ (NSString *)getDeviceMAC {
    int mib[6];
    size_t len;
    char *buf;
    unsigned char *ptr;
    struct if_msghdr *ifm;
    struct sockaddr_dl *sdl;
    
    mib[0] = CTL_NET;
    mib[1] = AF_ROUTE;
    mib[2] = 0;
    mib[3] = AF_LINK;
    mib[4] = NET_RT_IFLIST;
    
    if ((mib[5] = if_nametoindex("en0")) == 0) {
        printf("Error: if_nametoindex error\n");
        return NULL;
    }
    
    if (sysctl(mib, 6, NULL, &len, NULL, 0) < 0) {
        printf("Error: sysctl, take 1\n");
        return NULL;
    }
    
    if ((buf = malloc(len)) == NULL) {
        printf("Could not allocate memory. error!\n");
        return NULL;
    }
    
    if (sysctl(mib, 6, buf, &len, NULL, 0) < 0) {
        printf("Error: sysctl, take 2");
        free(buf);
        return NULL;
    }
    
    ifm = (struct if_msghdr *)buf;
    sdl = (struct sockaddr_dl *)(ifm + 1);
    ptr = (unsigned char *)LLADDR(sdl);
    NSString *macStr = [NSString stringWithFormat:@"%02X:%02X:%02X:%02X:%02X:%02X",*ptr, *(ptr+1), *(ptr+2), *(ptr+3), *(ptr+4), *(ptr+5)];
    free(buf);
    return macStr;
}

+ (NSString *)deviceVersion {
    struct utsname systemInfo;
    uname(&systemInfo);
    NSString *deviceString = [NSString stringWithCString:systemInfo.machine encoding:NSUTF8StringEncoding];
    
    //iPhone
    if ([deviceString isEqualToString:@"iPhone1,1"]) return @"iPhone2G";
    if ([deviceString isEqualToString:@"iPhone1,2"]) return @"iPhone3G";
    if ([deviceString isEqualToString:@"iPhone2,1"]) return @"iPhone3GS";
    if ([deviceString isEqualToString:@"iPhone3,1"]) return @"iPhone4";
    if ([deviceString isEqualToString:@"iPhone3,2"]) return @"iPhone4";
    if ([deviceString isEqualToString:@"iPhone3,3"]) return @"iPhone4";
    if ([deviceString isEqualToString:@"iPhone4,1"]) return @"iPhone4S";
    if ([deviceString isEqualToString:@"iPhone5,1"]) return @"iPhone5";
    if ([deviceString isEqualToString:@"iPhone5,2"]) return @"iPhone5";
    if ([deviceString isEqualToString:@"iPhone5,3"]) return @"iPhone5c";
    if ([deviceString isEqualToString:@"iPhone5,4"]) return @"iPhone5c";
    if ([deviceString isEqualToString:@"iPhone6,1"]) return @"iPhone5s";
    if ([deviceString isEqualToString:@"iPhone6,2"]) return @"iPhone5s";
    if ([deviceString isEqualToString:@"iPhone7,2"]) return @"iPhone6";
    if ([deviceString isEqualToString:@"iPhone7,1"]) return @"iPhone6Plus";
    if ([deviceString isEqualToString:@"iPhone8,1"]) return @"iPhone6s";
    if ([deviceString isEqualToString:@"iPhone8,2"]) return @"iPhone6sPlus";
    if ([deviceString isEqualToString:@"iPhone8,3"]) return @"iPhoneSE";
    if ([deviceString isEqualToString:@"iPhone8,4"]) return @"iPhoneSE";
    if ([deviceString isEqualToString:@"iPhone9,1"]) return @"iPhone7";
    if ([deviceString isEqualToString:@"iPhone9,2"]) return @"iPhone7Plus";
    if ([deviceString isEqualToString:@"iPhone10,1"]) return @"iPhone8";
    if ([deviceString isEqualToString:@"iPhone10,4"]) return @"iPhone8";
    if ([deviceString isEqualToString:@"iPhone10,2"]) return @"iPhone8Plus";
    if ([deviceString isEqualToString:@"iPhone10,5"]) return @"iPhone8Plus";
    if ([deviceString isEqualToString:@"iPhone10,3"]) return @"iPhoneX";
    if ([deviceString isEqualToString:@"iPhone10,6"]) return @"iPhoneX";
    
    return [NSString stringWithFormat:@"模拟器%@", deviceString];
}

+ (BOOL)isIphone {
    return [[self deviceVersion] containsString:@"iPhone"];
}

+ (NSString *)IPAddresses {
    
    int sockfd = socket(AF_INET, SOCK_DGRAM, 0);
    
    NSMutableArray *ips = [NSMutableArray array];
    
    int BUFFERSIZE = 4096;
    
    struct ifconf ifc;
    
    char buffer[BUFFERSIZE], *ptr, lastname[IFNAMSIZ], *cptr;
    
    struct ifreq *ifr, ifrcopy;
    
    ifc.ifc_len = BUFFERSIZE;
    ifc.ifc_buf = buffer;
    
    if (ioctl(sockfd, SIOCGIFCONF, &ifc) >= 0){
        
        for (ptr = buffer; ptr < buffer + ifc.ifc_len; ){
            
            ifr = (struct ifreq *)ptr;
            int len = sizeof(struct sockaddr);
            
            if (ifr->ifr_addr.sa_len > len) {
                len = ifr->ifr_addr.sa_len;
            }
            
            ptr += sizeof(ifr->ifr_name) + len;
            if (ifr->ifr_addr.sa_family != AF_INET) continue;
            if ((cptr = (char *)strchr(ifr->ifr_name, ':')) != NULL) *cptr = 0;
            if (strncmp(lastname, ifr->ifr_name, IFNAMSIZ) == 0) continue;
            
            memcpy(lastname, ifr->ifr_name, IFNAMSIZ);
            ifrcopy = *ifr;
            ioctl(sockfd, SIOCGIFFLAGS, &ifrcopy);
            
            if ((ifrcopy.ifr_flags & IFF_UP) == 0) continue;
            
            NSString *ip = [NSString  stringWithFormat:@"%s", inet_ntoa(((struct sockaddr_in *)&ifr->ifr_addr)->sin_addr)];
            [ips addObject:ip];
        }
    }
    
    close(sockfd);
    NSString *deviceIP = @"";
    
    for (int i=0; i < ips.count; i++) {
        if (ips.count > 0) {
            deviceIP = [NSString stringWithFormat:@"%@",ips.lastObject];
        }
    }
    return deviceIP;
}

+ (NSString *)telephonyNetwork {
    // 获取手机网络类型
    CTTelephonyNetworkInfo *info = [[CTTelephonyNetworkInfo alloc] init];
    NSString *currentStatus = info.currentRadioAccessTechnology;
    if ([currentStatus isEqualToString:@"CTRadioAccessTechnologyGPRS"])  return  @"GPRS";
    if ([currentStatus isEqualToString:@"CTRadioAccessTechnologyEdge"]) return @"2.75G EDGE";
    if ([currentStatus isEqualToString:@"CTRadioAccessTechnologyWCDMA"]) return  @"3G";
    if ([currentStatus isEqualToString:@"CTRadioAccessTechnologyHSDPA"]) return @"3.5G HSDPA";
    if ([currentStatus isEqualToString:@"CTRadioAccessTechnologyHSUPA"]) return @"3.5G HSUPA";
    if ([currentStatus isEqualToString:@"CTRadioAccessTechnologyCDMA1x"]) return @"2G";
    if ([currentStatus isEqualToString:@"CTRadioAccessTechnologyCDMAEVDORev0"]) return @"3G";
    if ([currentStatus isEqualToString:@"CTRadioAccessTechnologyCDMAEVDORevA"]) return @"3G";
    if ([currentStatus isEqualToString:@"CTRadioAccessTechnologyCDMAEVDORevB"]) return @"3G";
    if ([currentStatus isEqualToString:@"CTRadioAccessTechnologyeHRPD"])return @"HRPD";
    if ([currentStatus isEqualToString:@"CTRadioAccessTechnologyLTE"]) return @"4G";
    return @"未知";
}

@end
