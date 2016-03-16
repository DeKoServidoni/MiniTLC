//
//  PictureManager.swift
//  MiniTLC
//
//  Created by DeKo Servidoni on 3/15/16.
//  Copyright © 2016 DeKo Servidoni. All rights reserved.
//

import Foundation
import UIKit

/* Class responsible to manage the add and drag of the masks in the image */
class PictureManager {
    
    enum MaskType {
        case MaskHaroldinho
        case MaskLogo
        case MaskText
    }
    
    // MARK: Attributes
    
    var container: UIView
    
    // MARK: Constructor
    
    init(view: UIView) {
        container = view
    }
    
    // MARK: Public functions
    
    func addMask(type: MaskType) {
        
        var mask: UIImageView!
        
        switch type {
            
            case .MaskHaroldinho:
                mask = UIImageView(image: UIImage(named: "mask_haroldinho"))
                break
            
            case .MaskLogo:
                mask = UIImageView(image: UIImage(named: "mask_minitlc"))
                break
            
            case .MaskText:
                mask = UIImageView(image: UIImage(named: "mask_text"))
                break
        }
        
        container.addSubview(mask)
    }
}