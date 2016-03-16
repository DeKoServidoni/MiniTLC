//
//  PictureChooserViewController.swift
//  MiniTLC
//
//  Created by DeKo Servidoni on 3/8/16.
//  Copyright © 2016 DeKo Servidoni. All rights reserved.
//

import Foundation
import UIKit

class PictureChooserViewController: UIViewController, ImagePickerProtocol {
    
    // MARK: Attributes
    
    @IBOutlet weak var cameraButton: UIBarButtonItem!
    @IBOutlet weak var galleryButton: UIBarButtonItem!
    @IBOutlet weak var imageView: UIImageView!
    @IBOutlet weak var bottomToolbar: UIToolbar!
    
    @IBOutlet weak var buttonHaroldinho: UIButton!
    @IBOutlet weak var buttonLogo: UIButton!
    @IBOutlet weak var buttonText: UIButton!
    @IBOutlet weak var buttonContainer: UIVisualEffectView!
    
    var imagePickerDelegate: ImagePickerDelegate!
    var pictureManager: PictureManager!
    
    // MARK: Lifecycle functions
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // hide the masks bar
        hideMasks(true)
        
        pictureManager = PictureManager(view: view)
        imagePickerDelegate = ImagePickerDelegate(delegate: self)
        
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
    
    // MARK: Image picker delegate functions
    
    // set the image when it has been selected
    func onImageSelected(image: UIImage) {
        imageView.image = image
        hideMasks(false)
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
    
    // insert the haroldinho mask
    @IBAction func addHaroldinhoMask(sender: AnyObject) {
        pictureManager.addMask(PictureManager.MaskType.MaskHaroldinho)
    }
    
    // insert the minitlc logo mask
    @IBAction func addLogoMask(sender: AnyObject) {
        pictureManager.addMask(PictureManager.MaskType.MaskLogo)
    }
    
    // insert the ballon text mask
    @IBAction func addTextMask(sender: AnyObject) {
        pictureManager.addMask(PictureManager.MaskType.MaskText)
    }
    
    // MARK: Private functions
    
    // hide or show the masks bar
    private func hideMasks(hide: Bool) {
        buttonHaroldinho.hidden = hide
        buttonLogo.hidden = hide
        buttonText.hidden = hide
        buttonContainer.hidden = hide
    }
    
    // generate the image
    private func generatePicture() -> UIImage {
        
        bottomToolbar.hidden = true
        hideMasks(true)
        
        UIGraphicsBeginImageContext(view.frame.size)
        view.drawViewHierarchyInRect(view.frame, afterScreenUpdates: true)
        
        let generatedImage : UIImage!
        generatedImage = UIGraphicsGetImageFromCurrentImageContext()
        
        UIGraphicsEndImageContext()
        
        bottomToolbar.hidden = false
        hideMasks(false)
        
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