//
//  ImagePickerDelegate.swift
//  MiniTLC
//
//  Created by DeKo Servidoni on 3/14/16.
//  Copyright © 2016 DeKo Servidoni. All rights reserved.
//

import Foundation
import UIKit

class ImagePickerDelegate: NSObject, UIImagePickerControllerDelegate, UINavigationControllerDelegate {
    
    let imageHolder: UIImageView!
    
    init(view: UIImageView!) {
        imageHolder = view
    }
    
    // get the selected picture information
    func imagePickerController(picker: UIImagePickerController, didFinishPickingImage image: UIImage, editingInfo: [String : AnyObject]?) {
        imageHolder.image = image
        dismiss(picker)
    }
    
    // cancel the picker (dismiss it)
    func imagePickerControllerDidCancel(picker: UIImagePickerController) {
        dismiss(picker)
    }
    
    // dismiss the controller
    private func dismiss(picker: UIImagePickerController) {
        picker.dismissViewControllerAnimated(true, completion: nil)
    }
}