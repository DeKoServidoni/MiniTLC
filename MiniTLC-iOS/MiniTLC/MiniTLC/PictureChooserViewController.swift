//
//  PictureChooserViewController.swift
//  MiniTLC
//
//  Created by DeKo Servidoni on 3/8/16.
//  Copyright © 2016 DeKo Servidoni. All rights reserved.
//

import Foundation
import UIKit

class PictureChooserViewController: UIViewController {
    
    // MARK: Attributes
    
    @IBOutlet weak var cameraButton: UIBarButtonItem!
    @IBOutlet weak var galleryButton: UIBarButtonItem!
    @IBOutlet weak var imageView: UIImageView!
    @IBOutlet weak var bottomToolbar: UIToolbar!
    
    var imagePickerDelegate: ImagePickerDelegate!
    
    // MARK: Lifecycle functions
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        imagePickerDelegate = ImagePickerDelegate(view: imageView)
        
        // necessary to verify if the device have or not the camera
        // functionality. If don't have we need to disable the button, otherwise we enable it.
        cameraButton.enabled = UIImagePickerController.isSourceTypeAvailable(UIImagePickerControllerSourceType.Camera)
    }
    
    // MARK: Public functions
    
    // save the edited image to the gallery
    func saveImage() {
        
        if imageView.image != nil {
            
            let generated = generatePicture()
            
            let nextController = UIActivityViewController(activityItems: [generated], applicationActivities: nil)
            nextController.completionWithItemsHandler = { activity, success, items, error in
            
                if(success) {
                    self.dismissViewControllerAnimated(true, completion: nil)
                }
            }
        
            presentViewController(nextController, animated: true, completion: nil)
        }
    }
    
    // MARK: Action functions
    
    // open the camera to take a picture
    @IBAction func openCameraAction(sender: UIBarButtonItem) {
        openPicker(ofType: UIImagePickerControllerSourceType.Camera)
    }
    
    // open the gallery to choose a picture
    @IBAction func openGalleryAction(sender: UIBarButtonItem) {
        openPicker(ofType: UIImagePickerControllerSourceType.PhotoLibrary)
    }
    
    // MARK: Private functions
    
    // generate the image
    private func generatePicture() -> UIImage {
        
        bottomToolbar.hidden = true
        
        UIGraphicsBeginImageContext(view.frame.size)
        view.drawViewHierarchyInRect(view.frame, afterScreenUpdates: true)
        
        let generatedImage : UIImage!
        generatedImage = UIGraphicsGetImageFromCurrentImageContext()
        
        UIGraphicsEndImageContext()
        
        bottomToolbar.hidden = false
        
        return generatedImage
    }
    
    // open the picker with the desired source type
    private func openPicker(ofType type: UIImagePickerControllerSourceType!) {
        imageView.contentMode = UIViewContentMode.ScaleToFill;
        
        let pickerController = UIImagePickerController()
        pickerController.delegate = imagePickerDelegate
        pickerController.sourceType = type
        
        presentViewController(pickerController, animated: true, completion: nil)
    }
}