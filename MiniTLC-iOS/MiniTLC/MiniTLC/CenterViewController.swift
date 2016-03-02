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

class CenterViewController: UIViewController {
    
    // MARK: Class attributes
    
    var delegate: CenterViewControllerDelegate?
    
    // MARK: Action functions
    
    @IBAction func menuTapped(sender: UIBarButtonItem) {
        delegate?.toggleSidePanel()
    }
}