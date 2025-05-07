import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CommonFooterComponent } from './shared/common-footer/common-footer.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet,CommonFooterComponent],
  template: `<router-outlet></router-outlet>
    <!-- <app-common-footer></app-common-footer> -->
  `
})
export class AppComponent {
}
