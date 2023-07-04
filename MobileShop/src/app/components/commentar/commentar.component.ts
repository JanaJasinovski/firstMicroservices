import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Commentar } from 'src/app/models/Commentar';
import { CommentarService } from 'src/app/services/commentar.service';


@Component({
  selector: 'app-commentar',
  templateUrl: './commentar.component.html',
  styleUrls: ['./commentar.component.scss']
})
export class CommentarComponent implements OnInit {
  comments: Commentar[];
  message: string = '';
  id: number;

  ngOnInit(): void {
    this.router.paramMap.subscribe(params => {
        this.id = +params.get('id');
      });
    this.getCommentars();
  }

  constructor(private commentarService: CommentarService,
    private router: ActivatedRoute) {
}


  addComment(messageCreate: string) {
    messageCreate = this.message;
    console.log(messageCreate);
    
    this.commentarService.createCommentar(messageCreate, this.id).subscribe(
      data => {
        this.comments = data;
      }
    );      
    this.message = '';
  
  }

  getCommentars() {
    this.commentarService.getCommentars(this.id).subscribe(
      data => {
        this.comments = data;
      }
    )
  }
}
