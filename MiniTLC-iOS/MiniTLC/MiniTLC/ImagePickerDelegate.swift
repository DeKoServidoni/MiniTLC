//
//  ImagePickerDelegate.swift
//  MiniTLC
//
//  Created by DeKo Servidoni on 3/14/16.
//  Copyright © 2016 DeKo Servidoni. All rights reserved.
//

import Foundation
import UIKit

protocol ImagePickerProtocol {
    func onImageSelected(image: UIImage)
}

class ImagePickerDelegate: NSObject, UIImagePickerControllerDelegate, UINavigationControllerDelegate {
    
    var delegate: ImagePickerProtocol?
    
    init(delegate: ImagePickerProtocol?) {
        self.delegate = delegate
    }
    
    // get the selected picture information
    func imagePickerController(picker: UIImagePickerController, didFinishPickingImage image: UIImage, editingInfo: [String : AnyObject]?) {
        delegate?.onImageSelected(image)
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