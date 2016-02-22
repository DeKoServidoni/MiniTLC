//
//  ViewController.swift
//  MiniTLC
//
//  Created by DeKo Servidoni on 2/21/16.
//  Copyright © 2016 DeKo Servidoni. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    /* UI Components */
    @IBOutlet weak var menuButton: UIBarButtonItem!
    
    //MARK: Lifecycle methods
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        if self.revealViewController() != nil {
            menuButton.target = self.revealViewController()
            menuButton.action = "revealToggle:"
            self.view.addGestureRecognizer(self.revealViewController().panGestureRecognizer())
        }
    }
    
}

