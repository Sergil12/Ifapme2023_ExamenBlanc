import { NoteDto } from '../model/dto/note.dto';
import { Note } from '../model/business/note.business';

export class NoteHelper {
  // convertir un dto vers un business
  public static fromDTO(dto: NoteDto): Note {
    return {
      note_id: dto.note_id,
      cursus_name: dto.cursus_name,
      note: dto.note,
      isEmpty: false,
    };
  }
  //convertir un business vers un dto
  public static toDTO(business: Note): NoteDto {
    return {
      note_id: business.note_id,
      cursus_name: business.cursus_name,
      note: business.note,
    };
  }
  // retourne un business vide
  public static getEmpty(): Note {
    return {
      note_id: '',
      cursus_name: '',
      note: '',
      isEmpty: true,
    };
  }
}
