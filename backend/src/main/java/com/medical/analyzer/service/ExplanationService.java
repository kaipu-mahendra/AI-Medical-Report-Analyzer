package com.medical.analyzer.service;

import org.springframework.stereotype.Service;

@Service
public class ExplanationService {

    public String generateExplanation(String testName, String status, String value) {
        String normalizedName = testName.toUpperCase().replaceAll("[^A-Z0-9]", "");
        
        if ("NORMAL".equalsIgnoreCase(status)) {
            return "✓ Your " + testName + " is within normal range. Maintain a balanced diet and healthy lifestyle.";
        }
        
        // Comprehensive explanations with diet recommendations
        
        // HEMOGLOBIN / HAEMOGLOBIN
        if (normalizedName.contains("HEMOGLOBIN") || normalizedName.contains("HAEMOGLOBIN") || normalizedName.equals("HB") || normalizedName.equals("HGB")) {
            if ("LOW".equalsIgnoreCase(status)) {
                return "⚠ Low hemoglobin indicates possible anemia. This can cause fatigue, weakness, and shortness of breath. " +
                       "FOODS TO EAT: Spinach, red meat, liver, lentils, beans, fortified cereals, beetroot, pomegranate, dates, jaggery, eggs, and vitamin C rich fruits (oranges, lemons) to help iron absorption.";
            }
            if ("HIGH".equalsIgnoreCase(status)) {
                return "⚠ High hemoglobin may indicate dehydration, lung disease, or polycythemia. " +
                       "RECOMMENDATIONS: Stay well hydrated (8-10 glasses of water daily), avoid iron supplements, limit red meat, and consult a doctor.";
            }
        }
        
        // WBC / WHITE BLOOD CELLS
        if (normalizedName.contains("WBC") || normalizedName.contains("WHITEBLOOD") || normalizedName.contains("LEUCOCYTE") || normalizedName.contains("TLC")) {
            if ("LOW".equalsIgnoreCase(status)) {
                return "⚠ Low WBC count (Leukopenia) may indicate viral infections, bone marrow problems, or autoimmune disorders. " +
                       "FOODS TO EAT: Garlic, citrus fruits, yogurt, almonds, green tea, papaya, kiwi, spinach, broccoli, and foods rich in Omega-3 (fish, walnuts).";
            }
            if ("HIGH".equalsIgnoreCase(status)) {
                return "⚠ High WBC count indicates infection, inflammation, or stress response. " +
                       "FOODS TO EAT: Anti-inflammatory foods like turmeric, ginger, green leafy vegetables, berries, fatty fish, and avoid processed foods.";
            }
        }
        
        // RBC / RED BLOOD CELLS
        if (normalizedName.contains("RBC") || normalizedName.contains("REDBLOOD") || normalizedName.contains("ERYTHROCYTE")) {
            if ("LOW".equalsIgnoreCase(status)) {
                return "⚠ Low RBC count indicates anemia. This reduces oxygen-carrying capacity of blood. " +
                       "FOODS TO EAT: Iron-rich foods (spinach, liver, red meat), vitamin B12 (eggs, dairy, fish), folate (leafy greens, citrus), and copper (nuts, seeds, shellfish).";
            }
            if ("HIGH".equalsIgnoreCase(status)) {
                return "⚠ High RBC count (Polycythemia) may cause blood thickening. " +
                       "RECOMMENDATIONS: Stay hydrated, avoid smoking, limit iron intake, eat more fruits and vegetables, and consult a hematologist.";
            }
        }
        
        // PLATELETS
        if (normalizedName.contains("PLATELET") || normalizedName.contains("PLT") || normalizedName.contains("THROMBOCYTE")) {
            if ("LOW".equalsIgnoreCase(status)) {
                return "⚠ Low platelet count (Thrombocytopenia) increases bleeding risk. " +
                       "FOODS TO EAT: Papaya and papaya leaf extract, pumpkin, vitamin C foods (oranges, kiwi), vitamin K foods (spinach, broccoli), folate-rich foods, and avoid alcohol.";
            }
            if ("HIGH".equalsIgnoreCase(status)) {
                return "⚠ High platelet count (Thrombocytosis) increases clotting risk. " +
                       "FOODS TO EAT: Omega-3 fatty acids (salmon, walnuts), garlic, ginger, tomatoes, berries, and stay hydrated. Avoid vitamin K excess.";
            }
        }
        
        // ESR
        if (normalizedName.contains("ESR") || normalizedName.contains("SEDIMENTATIONRATE")) {
            if ("HIGH".equalsIgnoreCase(status)) {
                return "⚠ High ESR indicates inflammation, infection, or autoimmune conditions in the body. " +
                       "FOODS TO EAT: Anti-inflammatory diet - turmeric, ginger, omega-3 (fish, flaxseed), berries, leafy greens, olive oil. Avoid sugar and processed foods.";
            }
        }
        
        // MCV (Mean Corpuscular Volume)
        if (normalizedName.contains("MCV")) {
            if ("LOW".equalsIgnoreCase(status)) {
                return "⚠ Low MCV indicates microcytic anemia (small RBCs), often due to iron deficiency or thalassemia. " +
                       "FOODS TO EAT: Iron-rich foods (red meat, spinach, lentils, fortified cereals), vitamin C for absorption, and avoid tea/coffee with meals.";
            }
            if ("HIGH".equalsIgnoreCase(status)) {
                return "⚠ High MCV indicates macrocytic anemia (large RBCs), often due to B12 or folate deficiency. " +
                       "FOODS TO EAT: Vitamin B12 (eggs, fish, dairy, meat), folate (leafy greens, legumes, citrus), and fortified foods.";
            }
        }
        
        // MCH / MCHC
        if (normalizedName.contains("MCH") || normalizedName.contains("MCHC")) {
            if ("LOW".equalsIgnoreCase(status)) {
                return "⚠ Low MCH/MCHC indicates hypochromic anemia with less hemoglobin per cell. " +
                       "FOODS TO EAT: Iron-rich foods, vitamin B6 (poultry, fish, potatoes), and ensure adequate protein intake.";
            }
            if ("HIGH".equalsIgnoreCase(status)) {
                return "⚠ High MCH/MCHC may indicate spherocytosis or B12 deficiency. " +
                       "RECOMMENDATIONS: Increase B12 and folate intake, stay hydrated, and consult a doctor.";
            }
        }
        
        // NEUTROPHILS
        if (normalizedName.contains("NEUTROPHIL")) {
            if ("LOW".equalsIgnoreCase(status)) {
                return "⚠ Low neutrophils (Neutropenia) reduce infection-fighting ability. " +
                       "FOODS TO EAT: Protein-rich foods, vitamin B12, folate, zinc (nuts, seeds), and maintain good hygiene to prevent infections.";
            }
            if ("HIGH".equalsIgnoreCase(status)) {
                return "⚠ High neutrophils indicate bacterial infection or inflammation. " +
                       "RECOMMENDATIONS: Rest, hydration, anti-inflammatory foods (turmeric, ginger), and treat underlying infection.";
            }
        }
        
        // LYMPHOCYTES
        if (normalizedName.contains("LYMPHOCYTE")) {
            if ("LOW".equalsIgnoreCase(status)) {
                return "⚠ Low lymphocytes weaken immune response. " +
                       "FOODS TO EAT: Protein-rich foods, zinc (pumpkin seeds, chickpeas), vitamin C (citrus), and probiotics (yogurt, kefir).";
            }
            if ("HIGH".equalsIgnoreCase(status)) {
                return "⚠ High lymphocytes may indicate viral infection or lymphoma. " +
                       "RECOMMENDATIONS: Rest, hydration, immune-boosting foods, and consult a doctor if persistent.";
            }
        }
        
        // EOSINOPHILS
        if (normalizedName.contains("EOSINOPHIL")) {
            if ("HIGH".equalsIgnoreCase(status)) {
                return "⚠ High eosinophils often indicate allergies, parasitic infection, or asthma. " +
                       "RECOMMENDATIONS: Identify and avoid allergens, anti-inflammatory diet, probiotics, and consider allergy testing.";
            }
        }
        
        // MONOCYTES
        if (normalizedName.contains("MONOCYTE")) {
            if ("HIGH".equalsIgnoreCase(status)) {
                return "⚠ High monocytes indicate chronic infection or inflammation. " +
                       "FOODS TO EAT: Anti-inflammatory foods, omega-3, vitamin D, and adequate rest.";
            }
        }
        
        // BASOPHILS
        if (normalizedName.contains("BASOPHIL")) {
            if ("HIGH".equalsIgnoreCase(status)) {
                return "⚠ High basophils may indicate allergic reactions or myeloproliferative disorders. " +
                       "RECOMMENDATIONS: Identify allergens, anti-inflammatory diet, and consult a doctor.";
            }
        }
        
        // PCV / HCT (Hematocrit)
        if (normalizedName.contains("PCV") || normalizedName.contains("HCT") || normalizedName.contains("HEMATOCRIT") || normalizedName.contains("PACKEDCELL")) {
            if ("LOW".equalsIgnoreCase(status)) {
                return "⚠ Low PCV/Hematocrit indicates anemia or blood loss. " +
                       "FOODS TO EAT: Iron-rich foods, B12, folate, beetroot juice, pomegranate, and adequate hydration.";
            }
            if ("HIGH".equalsIgnoreCase(status)) {
                return "⚠ High PCV indicates dehydration or polycythemia. " +
                       "RECOMMENDATIONS: Increase water intake, avoid smoking, and limit iron-rich foods.";
            }
        }
        
        // GLUCOSE / BLOOD SUGAR
        if (normalizedName.contains("GLUCOSE") || normalizedName.contains("SUGAR") || normalizedName.contains("FBS") || normalizedName.contains("RBS") || normalizedName.contains("PPBS")) {
            if ("LOW".equalsIgnoreCase(status)) {
                return "⚠ Low blood sugar (Hypoglycemia) can cause dizziness and weakness. " +
                       "FOODS TO EAT: Complex carbs (whole grains, oats), fruits, frequent small meals, and avoid skipping meals.";
            }
            if ("HIGH".equalsIgnoreCase(status)) {
                return "⚠ High blood sugar indicates diabetes risk or poor glucose control. " +
                       "FOODS TO EAT: Low glycemic foods, whole grains, leafy greens, cinnamon, fenugreek, bitter gourd, avoid sugar and refined carbs.";
            }
        }
        
        // CHOLESTEROL
        if (normalizedName.contains("CHOLESTEROL")) {
            if ("HIGH".equalsIgnoreCase(status)) {
                return "⚠ High cholesterol increases heart disease and stroke risk. " +
                       "FOODS TO EAT: Oats, barley, almonds, walnuts, olive oil, fatty fish, flaxseed, fruits, vegetables. AVOID: Fried foods, red meat, full-fat dairy.";
            }
        }
        
        // TRIGLYCERIDES
        if (normalizedName.contains("TRIGLYCERIDE")) {
            if ("HIGH".equalsIgnoreCase(status)) {
                return "⚠ High triglycerides increase heart disease risk. " +
                       "FOODS TO EAT: Omega-3 (fish, flaxseed), reduce sugar and refined carbs, avoid alcohol, increase fiber intake.";
            }
        }
        
        // CREATININE
        if (normalizedName.contains("CREATININE")) {
            if ("HIGH".equalsIgnoreCase(status)) {
                return "⚠ High creatinine indicates kidney function issues. " +
                       "FOODS TO EAT: Low protein diet, cucumbers, cauliflower, cabbage, garlic. AVOID: Red meat, high sodium foods, and stay hydrated.";
            }
        }
        
        // UREA / BUN
        if (normalizedName.contains("UREA") || normalizedName.contains("BUN")) {
            if ("HIGH".equalsIgnoreCase(status)) {
                return "⚠ High urea/BUN indicates kidney issues or dehydration. " +
                       "RECOMMENDATIONS: Increase water intake, reduce protein consumption, eat more fruits and vegetables.";
            }
        }
        
        // URIC ACID
        if (normalizedName.contains("URICACID")) {
            if ("HIGH".equalsIgnoreCase(status)) {
                return "⚠ High uric acid can cause gout and kidney stones. " +
                       "FOODS TO EAT: Cherries, berries, low-fat dairy, coffee. AVOID: Red meat, organ meats, shellfish, alcohol, sugary drinks.";
            }
        }
        
        // BILIRUBIN
        if (normalizedName.contains("BILIRUBIN")) {
            if ("HIGH".equalsIgnoreCase(status)) {
                return "⚠ High bilirubin indicates liver issues or hemolysis. " +
                       "FOODS TO EAT: Fruits, vegetables, whole grains, coffee, adequate water. AVOID: Alcohol, fatty foods, and fried items.";
            }
        }
        
        // SGPT / ALT
        if (normalizedName.contains("SGPT") || normalizedName.contains("ALT")) {
            if ("HIGH".equalsIgnoreCase(status)) {
                return "⚠ High SGPT/ALT indicates liver stress or damage. " +
                       "FOODS TO EAT: Leafy greens, garlic, grapefruit, green tea, walnuts, turmeric. AVOID: Alcohol, fried foods, excessive medications.";
            }
        }
        
        // SGOT / AST
        if (normalizedName.contains("SGOT") || normalizedName.contains("AST")) {
            if ("HIGH".equalsIgnoreCase(status)) {
                return "⚠ High SGOT/AST indicates liver or muscle damage. " +
                       "FOODS TO EAT: Antioxidant-rich foods, vegetables, fruits, nuts, whole grains. AVOID: Alcohol and processed foods.";
            }
        }
        
        // THYROID - TSH
        if (normalizedName.contains("TSH")) {
            if ("LOW".equalsIgnoreCase(status)) {
                return "⚠ Low TSH may indicate hyperthyroidism (overactive thyroid). " +
                       "FOODS TO EAT: Cruciferous vegetables (broccoli, cabbage), selenium-rich foods (Brazil nuts). AVOID: Iodine-rich foods, caffeine.";
            }
            if ("HIGH".equalsIgnoreCase(status)) {
                return "⚠ High TSH indicates hypothyroidism (underactive thyroid). " +
                       "FOODS TO EAT: Iodine (seafood, iodized salt), selenium (Brazil nuts), zinc, avoid goitrogens (raw cabbage, soy).";
            }
        }
        
        // VITAMIN D
        if (normalizedName.contains("VITAMIND") || normalizedName.contains("VITD") || normalizedName.contains("25OH")) {
            if ("LOW".equalsIgnoreCase(status)) {
                return "⚠ Low Vitamin D causes weak bones, fatigue, and weak immunity. " +
                       "RECOMMENDATIONS: 15-20 min sunlight exposure, egg yolks, fatty fish, fortified milk, mushrooms, and vitamin D supplements.";
            }
        }
        
        // VITAMIN B12
        if (normalizedName.contains("B12") || normalizedName.contains("COBALAMIN")) {
            if ("LOW".equalsIgnoreCase(status)) {
                return "⚠ Low B12 causes fatigue, weakness, neurological issues. " +
                       "FOODS TO EAT: Eggs, fish, meat, dairy, fortified cereals. Vegetarians may need B12 supplements.";
            }
        }
        
        // IRON / FERRITIN
        if (normalizedName.contains("IRON") || normalizedName.contains("FERRITIN")) {
            if ("LOW".equalsIgnoreCase(status)) {
                return "⚠ Low iron/ferritin causes iron-deficiency anemia. " +
                       "FOODS TO EAT: Red meat, spinach, lentils, beans, fortified cereals, pumpkin seeds, dark chocolate. Take with vitamin C for better absorption.";
            }
            if ("HIGH".equalsIgnoreCase(status)) {
                return "⚠ High iron can damage organs over time. " +
                       "RECOMMENDATIONS: Reduce red meat, avoid iron supplements, limit vitamin C with meals, regular blood donation may help.";
            }
        }
        
        // CALCIUM
        if (normalizedName.contains("CALCIUM")) {
            if ("LOW".equalsIgnoreCase(status)) {
                return "⚠ Low calcium affects bone health and muscle function. " +
                       "FOODS TO EAT: Dairy products, fortified plant milk, leafy greens, almonds, sesame seeds, ragi, and get adequate vitamin D.";
            }
            if ("HIGH".equalsIgnoreCase(status)) {
                return "⚠ High calcium may indicate parathyroid issues. " +
                       "RECOMMENDATIONS: Stay hydrated, reduce calcium supplements, limit dairy temporarily, and consult a doctor.";
            }
        }
        
        // Default fallback
        if ("LOW".equalsIgnoreCase(status)) {
            return "⚠ " + testName + " is below normal range. Please consult your doctor for personalized advice and dietary recommendations.";
        }
        if ("HIGH".equalsIgnoreCase(status)) {
            return "⚠ " + testName + " is above normal range. Please consult your doctor for personalized advice and dietary recommendations.";
        }

        return "Value is " + status + ". Please consult a doctor for proper evaluation.";
    }
}
