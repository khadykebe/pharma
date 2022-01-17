<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Facture extends Model
{
    use HasFactory;
    protected $fillable = [
        'codeClient',
        'date',
        'montant',
        
    ];


    public function facturev()
    {
        return $this->belongsTo(Vente::class);
    }
    public function facturep()
    {
        return $this->belongsTo(Produit::class);
    }
}
