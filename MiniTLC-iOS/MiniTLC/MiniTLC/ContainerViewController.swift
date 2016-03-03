//
//  ContainerViewController.swift
//  MiniTLC
//
//  Created by DeKo Servidoni on 2/22/16.
//  Copyright © 2016 DeKo Servidoni. All rights reserved.
//

import Foundation
import UIKit

/* 
    Class responsible to hold all the slide menu functionality of the application 
    Reference: http://www.raywenderlich.com/78568/create-slide-out-navigation-panel-swift
*/
class ContainerViewController: UIViewController, CenterViewControllerDelegate {
    
    enum SlideOutState {
        case Collapsed
        case SidePanelExpanded
    }
    
    // MARK: Class attributes
    
    let centerPanelExpandedOffset: CGFloat = 70
    
    var centerNavigationController: UINavigationController!
    var centerViewController: CenterViewController!
    var sidePanelViewController: SidePanelViewController?
    
    var currentState: SlideOutState = .Collapsed {
        didSet {
            let shouldShowShadow = currentState != .Collapsed
            centerNavigationController.view.layer.shadowOpacity = (shouldShowShadow) ? 0.8 : 0.0
        }
    }
    
    // MARK: Lifecycle functions
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        centerViewController = UIStoryboard.centerViewController()
        centerViewController.delegate = self
        
        centerNavigationController = UINavigationController(rootViewController: centerViewController)
        centerNavigationController.navigationBar.barTintColor = UIColor(hex: 0xF44336)
        centerNavigationController.navigationBar.backgroundColor = UIColor(hex: 0xF44336)
        centerNavigationController.navigationBar.tintColor = UIColor(hex: 0xFFFFFF)
        centerNavigationController.navigationBar.titleTextAttributes = [NSForegroundColorAttributeName : UIColor.whiteColor()]
        
        view.addSubview(centerNavigationController.view)
        addChildViewController(centerNavigationController)

        centerNavigationController.didMoveToParentViewController(self)
    }
    
    // MARK: CenterViewController delegate
    /* Implementation of the content of the delegate necessary functions */
    
    // open the side panel
    func toggleSidePanel() {
        
        let notAlreadyExpanded = (currentState != .SidePanelExpanded)
        
        if notAlreadyExpanded {
            addSidePanel()
        }
        
        // Do the animation to scroll to the right the side panel
        if notAlreadyExpanded {
            currentState = .SidePanelExpanded
            animateCenterPanelXPosition(CGRectGetWidth(centerNavigationController.view.frame) - centerPanelExpandedOffset)
            
        } else {
            animateCenterPanelXPosition(0) { finished in
                self.currentState = .Collapsed
                
                self.sidePanelViewController!.view.removeFromSuperview()
                self.sidePanelViewController = nil;
            }
        }
    }
    
    
    // collapse the side panel if it's open
    func collapseSidePanels() {
        if currentState == .SidePanelExpanded {
            toggleSidePanel()
        }
    }

    
    // MARK: Helper functions
    
    /* Insert the side panel as a subview in this view controller */
    func addSidePanel() {
        
        if sidePanelViewController == nil {
            sidePanelViewController = UIStoryboard.sideViewController()
            
            view.insertSubview(sidePanelViewController!.view, atIndex: 0)
            
            addChildViewController(sidePanelViewController!)
            sidePanelViewController!.didMoveToParentViewController(self)
        }
    }

    /* Do the animation to scroll to the right the side panel */
    func animateCenterPanelXPosition(targetPosition: CGFloat, completion: ((Bool) -> Void)! = nil) {
        
        UIView.animateWithDuration(0.5, delay: 0, usingSpringWithDamping: 0.8, initialSpringVelocity: 0, options: .CurveEaseInOut, animations: {
            self.centerNavigationController.view.frame.origin.x = targetPosition
        }, completion: completion)
    }
}

/* Extension used to enable to enter colors with HEXA values */
extension UIColor {
    
    convenience init(hex: Int) {
        let components = (
            R: CGFloat((hex >> 16) & 0xff) / 255,
            G: CGFloat((hex >> 08) & 0xff) / 255,
            B: CGFloat((hex >> 00) & 0xff) / 255
        )
        self.init(red: components.R, green: components.G, blue: components.B, alpha: 1)
    }
    
}

/* Extension used to add a couple of functions for the StoryBoard representing class */
private extension UIStoryboard {
    
    class func mainStoryboard() -> UIStoryboard {
        return UIStoryboard(name: "Main", bundle: NSBundle.mainBundle())
    }
    
    class func sideViewController() -> SidePanelViewController? {
        return mainStoryboard().instantiateViewControllerWithIdentifier("SidePanelViewController") as? SidePanelViewController
    }
    
    class func centerViewController() -> CenterViewController? {
        return mainStoryboard().instantiateViewControllerWithIdentifier("CenterViewController") as? CenterViewController
    }
}