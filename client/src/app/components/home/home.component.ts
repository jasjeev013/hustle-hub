import { Component,AfterViewInit  } from '@angular/core';
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
      interval: 3000,  // Time between automatic transitions (optional)
      ride: 'carousel'
    });
  }
}
