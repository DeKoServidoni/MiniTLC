//
//  ContainerViewController.swift
//  MiniTLC
//
//  Created by DeKo Servidoni on 2/22/16.
//  Copyright © 2016 DeKo Servidoni. All rights reserved.
//

import Foundation

class ContainerViewController: UIViewController {
    
    var screenEdgeRecognizer: UIScreenEdgePanGestureRecognizer!
    
    @IBOutlet weak var rightContainer: UIView!
    @IBOutlet weak var leftContainer: UIView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        screenEdgeRecognizer = UIScreenEdgePanGestureRecognizer(target: self, action: "swipe:")
        screenEdgeRecognizer.edges = .Left
        view.addGestureRecognizer(screenEdgeRecognizer)
        
        let height = leftContainer.frame.size.height
        let width = leftContainer.frame.size.width
        
        UIView.animateWithDuration(1.0, animations: {
            
            self.rightContainer.frame = CGRectMake(0, 0, height, width)
            
        })
        
    }
    
    func swipe(sender: UIScreenEdgePanGestureRecognizer) {
        
        // how much distance have we panned in reference to the parent view?
        //let translation = sender.translationInView(sender.view!)
        
        // do some math to translate this to a percentage based value
        //let d =  translation.x / CGRectGetWidth(sender.view!.bounds) * 0.5
    }
}