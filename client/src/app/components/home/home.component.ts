import { Component,AfterViewInit, OnInit, inject  } from '@angular/core';
import { UserService } from 'src/app/service/user.service';
declare var bootstrap: any;

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements AfterViewInit {




  ngAfterViewInit(): void {
    const myCarouselElement = document.querySelector('#carouselExampleCaptions');
    const carousel = new bootstrap.Carousel(myCarouselElement, {
      interval: 2000,  // Time between automatic transitions (optional)
      ride: 'carousel'
    });
  }

  
}
