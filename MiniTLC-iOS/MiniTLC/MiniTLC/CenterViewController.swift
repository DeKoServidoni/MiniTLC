//
//  CenterViewController.swift
//  MiniTLC
//
//  Created by DeKo Servidoni on 3/1/16.
//  Copyright © 2016 DeKo Servidoni. All rights reserved.
//

import Foundation
import UIKit

protocol CenterViewControllerDelegate {
    func toggleSidePanel()
    func collapseSidePanels()
}

class CenterViewController: UIViewController, SidePanelViewControllerDelegate {
    
    // MARK: Class attributes
    
    @IBOutlet weak var containerView: UIView!
    
    var delegate: CenterViewControllerDelegate?
    
    private var activeViewController: UIViewController? {
        didSet {
            removeInactiveViewController(oldValue)
            updateActiveViewController()
        }
    }
    
    // MARK: Lifecycle functions
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // load the default view controller (Home)
        let loadedStoryboard = UIStoryboard(name: "Main", bundle: NSBundle.mainBundle())
        let controller = loadedStoryboard.instantiateViewControllerWithIdentifier("HomeViewController") as? HomeViewController
        activeViewController = controller
    }
    
    // MARK: Action functions
    
    @IBAction func menuTapped(sender: UIBarButtonItem) {
        delegate?.toggleSidePanel()
    }
    
    // MARK: Side panel delegate functions
    
    func openViewController(controller: UIViewController!) {
        
        if controller != nil {
            // set the desired view controller
            activeViewController = controller
            
            if controller.isMemberOfClass(PictureChooserViewController) {
                navigationItem.rightBarButtonItem = UIBarButtonItem(title: "Salvar", style: UIBarButtonItemStyle.Plain, target: (controller as! PictureChooserViewController), action:"saveImage")
            } else {
                navigationItem.rightBarButtonItem = nil
            }
            
            // close the panel
            delegate?.collapseSidePanels()
        }
    }
    
    // MARK: Private functions
    
    private func removeInactiveViewController(inactiveViewController: UIViewController?) {
        if let inActiveVC = inactiveViewController {
            // call before removing child view controller's view from hierarchy
            inActiveVC.willMoveToParentViewController(nil)
            
            inActiveVC.view.removeFromSuperview()
            
            // call after removing child view controller's view from hierarchy
            inActiveVC.removeFromParentViewController()
        }
    }
    
    private func updateActiveViewController() {
        if let activeVC = activeViewController {
            // call before adding child view controller's view as subview
            addChildViewController(activeVC)
            
            activeVC.view.frame = containerView.bounds
            containerView.addSubview(activeVC.view)
            
            // call before adding child view controller's view as subview
            activeVC.didMoveToParentViewController(self)
        }
    }
}