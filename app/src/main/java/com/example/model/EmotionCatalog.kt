package com.example.model

object EmotionCatalog {

    private fun e(
        id: String,
        name: String,
        cat: EmotionCategory,
        v: Float,
        a: Float,
        som: String,
        purp: String,
        hint: String
    ) = Emotion(id, name, cat, v, a, som, purp, hint)

    val allEmotions: List<Emotion> by lazy {
        val list = mutableListOf<Emotion>()
        
        // 1. HAPPY & JOYFUL (42 emotions)
        val happyData = listOf(
            Triple("joyful", "Joyful", Triple(0.85f, 0.7f, "Warm chest expanding, soft upward gaze|Celebration of thriving life|Radiate and share warmth with those around you")),
            Triple("happy", "Happy", Triple(0.80f, 0.6f, "Relaxed jaw, natural smile, light limbs|Positive evaluation of present state|Savor this wholesome sensation")),
            Triple("ecstatic", "Ecstatic", Triple(0.95f, 0.9f, "Surging pulse, buoyant chest, tingling skin|Peak celebration of life and triumph|Channel overflowing vitality creatively")),
            Triple("elated", "Elated", Triple(0.90f, 0.8f, "Light-footedness, expansive posture|Elevation and uplifted spirit|Anchor this peak experience in memory")),
            Triple("euphoric", "Euphoric", Triple(0.95f, 0.95f, "Intense warmth throughout, dopamine rush|Profound sense of well-being|Breathe deeply to ground the high energy")),
            Triple("delighted", "Delighted", Triple(0.85f, 0.65f, "Sparkling eyes, crinkled smile|Pleasure from unexpected goodness|Express your gratitude openly")),
            Triple("cheerful", "Cheerful", Triple(0.75f, 0.55f, "Lightness in facial muscles, pleasant cadence|Social warmth and optimism|Share an encouraging word")),
            Triple("gleeful", "Gleeful", Triple(0.85f, 0.75f, "Chuckling throat, bouncing posture|Spontaneous innocent delight|Enjoy the playful lightness")),
            Triple("blissful", "Blissful", Triple(0.90f, 0.35f, "Soft whole-body relaxation, peaceful heartbeat|Deep harmony with existence|Rest in silent contentment")),
            Triple("content", "Content", Triple(0.70f, 0.25f, "Gentle belly breathing, slack shoulders|Appreciation of 'enoughness'|Notice what is already complete")),
            Triple("satisfied", "Satisfied", Triple(0.75f, 0.35f, "Settled stomach, deep exhalation|Completion of a meaningful task|Acknowledge your progress")),
            Triple("fulfilled", "Fulfilled", Triple(0.85f, 0.40f, "Warm fullness in torso, grounded alignment|Alignment with core values|Reflect on the journey accomplished")),
            Triple("radiant", "Radiant", Triple(0.88f, 0.75f, "Glow in cheeks, open collarbones|Expressing inner light|Let your energy uplift the room")),
            Triple("jubilant", "Jubilant", Triple(0.92f, 0.85f, "Exultant voice, raised arms|Shared triumph and collective joy|Celebrate together")),
            Triple("exuberant", "Exuberant", Triple(0.88f, 0.80f, "High energy bounce, dynamic speech|Abundant enthusiasm and play|Direct this stamina into meaningful art")),
            Triple("optimistic", "Optimistic", Triple(0.75f, 0.55f, "Forward-facing posture, open gaze|Anticipation of favorable outcomes|Draft actionable steps for future plans")),
            Triple("hopeful", "Hopeful", Triple(0.70f, 0.50f, "Gentle lift in sternum, steady breath|Belief in positive possibilities|Nurture what you can influence")),
            Triple("buoyant", "Buoyant", Triple(0.80f, 0.65f, "Floating feeling in abdomen, quick steps|Resilient lightness over adversity|Ride the emotional wave upward")),
            Triple("playful", "Playful", Triple(0.80f, 0.70f, "Mischievous grin, nimble gestures|Exploration and social bonding|Engage in lighthearted curiosity")),
            Triple("vibrant", "Vibrant", Triple(0.85f, 0.75f, "Tingling vitality, vivid senses|Awakened biological energy|Celebrate your physical readiness")),
            Triple("enthusiastic", "Enthusiastic", Triple(0.80f, 0.80f, "Fast pulse, eager nodding, bright tone|Passion for an upcoming endeavor|Capture your ideas while inspired")),
            Triple("thrilled", "Thrilled", Triple(0.88f, 0.85f, "Fluttering chest, electric nerves|Anticipation of exciting novelty|Channel excitement into deliberate action")),
            Triple("triumphant", "Triumphant", Triple(0.90f, 0.80f, "Expanded spine, raised chin|Overcoming major resistance|Honor the effort it took")),
            Triple("gratified", "Gratified", Triple(0.78f, 0.40f, "Sigh of relief, gentle warmth in throat|Reward from patience and labor|Enjoy the deserved fruit of labor")),
            Triple("lighthearted", "Lighthearted", Triple(0.75f, 0.45f, "Easy laughter, free movement|Relief from heaviness and tension|Maintain this balanced simplicity")),
            Triple("merry", "Merry", Triple(0.80f, 0.60f, "Warm flushed face, hearty speech|Festive and communal cheer|Connect and toast with friends")),
            Triple("sunny", "Sunny", Triple(0.78f, 0.50f, "Relaxed brow, clear focus|Bright clarity without cloudiness|Spread your steady goodwill")),
            Triple("bubbly", "Bubbly", Triple(0.82f, 0.75f, "Sparking voice, rhythmic pacing|Effervescent spirit and charm|Invite others into the fun")),
            Triple("proud", "Proud", Triple(0.80f, 0.60f, "Upright spine, broad chest|Healthy self-regard and dignity|Hold your dignity with humility")),
            Triple("rejoicing", "Rejoicing", Triple(0.90f, 0.80f, "Full open smile, tear of joy|Deep gratitude for answered hope|Give thanks for grace received")),
            Triple("blessed", "Blessed", Triple(0.85f, 0.35f, "Bowed head in awe, peaceful chest|Humility in the presence of good|Pay the blessing forward")),
            Triple("enchanted", "Enchanted", Triple(0.85f, 0.60f, "Softened eyes, transfixed focus|Captivated by beauty and wonder|Take a mental snapshot of this beauty")),
            Triple("overjoyed", "Overjoyed", Triple(0.92f, 0.85f, "Overwhelming pleasant chest rush|Receiving deeply desired news|Let happy tears flow freely")),
            Triple("invigorated", "Invigorated", Triple(0.80f, 0.75f, "Deep lung capacity, strong pulse|Renewed physical stamina|Tackle a task you were putting off")),
            Triple("carefree", "Carefree", Triple(0.78f, 0.35f, "Loose joints, easy strolling|Freedom from burden or pressure|Soak in the rare freedom")),
            Triple("gladsome", "Gladsome", Triple(0.76f, 0.50f, "Warm palms, sweet demeanor|Quiet genuine happiness|Cherish quiet moments")),
            Triple("harmonious", "Harmonious", Triple(0.82f, 0.30f, "Unified sensation, synchronized rhythm|Inner and outer alignment|Stay in flow with the day")),
            Triple("chipper", "Chipper", Triple(0.72f, 0.60f, "Snappy movements, alert ears|Brisk morning readiness|Start your key priorities early")),
            Triple("zestful", "Zestful", Triple(0.84f, 0.80f, "Crisp sensory sharpness, eager stride|Appetite for life experience|Dive into an invigorating project")),
            Triple("radiating", "Radiating", Triple(0.88f, 0.70f, "Centrifugal warmth from heart outward|Infectious positive vitality|Warm the space around you")),
            Triple("beaming", "Beaming", Triple(0.86f, 0.65f, "Broad genuine smile, glowing gaze|Uncontainable happiness|Share a sincere compliment")),
            Triple("transcendent", "Transcendent Joy", Triple(0.96f, 0.75f, "Loss of boundary sensation, vast peace|Connection to the universal good|Rest in sacred gratitude"))
        )
        for (item in happyData) {
            val parts = item.third.third.split("|")
            list.add(e(item.first, item.second, EmotionCategory.JOY_CONTENTMENT, item.third.first, item.third.second, parts[0], parts[1], parts[2]))
        }

        // 2. CALM & PEACEFUL (40 emotions)
        val calmData = listOf(
            Triple("peaceful", "Peaceful", Triple(0.75f, 0.2f, "Slow deep breathing, relaxed jaw|Equilibrium in nervous system|Preserve this sacred quietness")),
            Triple("calm", "Calm", Triple(0.70f, 0.2f, "Soft shoulders, low muscle tension|Readiness to respond without panic|Anchor in your breathing rhythm")),
            Triple("serene", "Serene", Triple(0.85f, 0.15f, "Unclenched brow, still body|Untroubled mental clarity|Observe the stillness around you")),
            Triple("tranquil", "Tranquil", Triple(0.80f, 0.15f, "Slow steady heart rate, calm eyes|Restoration of autonomic balance|Enjoy gentle silence")),
            Triple("relaxed", "Relaxed", Triple(0.65f, 0.25f, "Heavy comfortable limbs, soft stomach|Parasympathetic rest state|Allow bodily recovery")),
            Triple("placid", "Placid", Triple(0.60f, 0.15f, "Unrippled breath, neutral face|Unbothered by small disturbances|Maintain your quiet footing")),
            Triple("centered", "Centered", Triple(0.78f, 0.3f, "Grounding in the pelvic floor and core|Stable axis of self-reference|Operate from this internal center")),
            Triple("grounded", "Grounded", Triple(0.75f, 0.25f, "Weight in feet, connected to earth|Physical and emotional solidity|Feel the support of gravity")),
            Triple("still", "Still", Triple(0.70f, 0.10f, "Suspended movement, quiet mind|Space between stimuli and action|Rest in the quiet stillness")),
            Triple("composed", "Composed", Triple(0.72f, 0.35f, "Deliberate posture, steady voice|Mastery over impulsive reactions|Speak with calm clarity")),
            Triple("unruffled", "Unruffled", Triple(0.68f, 0.25f, "Smooth breathing despite chaos|Resistance to external storms|Observe without getting entangled")),
            Triple("reposed", "Reposed", Triple(0.74f, 0.15f, "Relaxed spine, resting palms|Recharging vital reserve|Give yourself permission to pause")),
            Triple("settled", "Settled", Triple(0.70f, 0.20f, "Absence of restlessness, calm gut|Resolution of conflict or movement|Sink comfortably into your chair")),
            Triple("equanimous", "Equanimous", Triple(0.82f, 0.25f, "Balanced awareness, neutral posture|Wisdom of non-reactivity|Welcome everything with even mind")),
            Triple("mellow", "Mellow", Triple(0.65f, 0.20f, "Gentle warm tone, unhurried pace|Absence of sharp edges|Savor the soft afternoon light")),
            Triple("rested", "Rested", Triple(0.75f, 0.20f, "Refreshed eyes, recharged strength|Restoration of biological energy|Channel energy smoothly")),
            Triple("soothed", "Soothed", Triple(0.72f, 0.22f, "Warm comfort in chest and throat|Relief from prior distress|Keep soothing sensory inputs close")),
            Triple("quiet", "Quiet", Triple(0.62f, 0.12f, "Softened auditory focus, silence|Withdrawal from overstimulation|Protect your quiet sanctuary")),
            Triple("steady", "Steady", Triple(0.72f, 0.30f, "Unwavering hands, firm stance|Reliability under load|Continue with rhythmic progress")),
            Triple("untroubled", "Untroubled", Triple(0.74f, 0.18f, "Light forehead, relaxed temples|Absence of chronic worry|Cherish peace in the present")),
            Triple("easygoing", "Easygoing", Triple(0.66f, 0.30f, "Loose walk, open humorous smirk|Flexibility in social flow|Adapt smoothly to unexpected changes")),
            Triple("at_ease", "At Ease", Triple(0.72f, 0.22f, "Dropping of defensive tension|Safety in the present environment|Trust your surroundings")),
            Triple("meditative", "Meditative", Triple(0.80f, 0.15f, "Focused attention on breath|Deep internal contemplation|Witness thoughts like passing clouds")),
            Triple("zen", "Zen", Triple(0.84f, 0.18f, "Presence in simple sensations|Integration of being and doing|Drink your tea mindfully")),
            Triple("harmonized", "Harmonized", Triple(0.78f, 0.25f, "Coherent biological rhythm|Congruence across mind and body|Stay in tuned alignment")),
            Triple("gentle", "Gentle", Triple(0.70f, 0.25f, "Soft touch, tender voice|Non-violence toward self and others|Handle yourself with care")),
            Triple("pacified", "Pacified", Triple(0.64f, 0.20f, "Ceasing of internal dispute|Quelling of turbulent emotions|Let bygones be bygones")),
            Triple("unfazed", "Unfazed", Triple(0.68f, 0.32f, "Steady pulse, unbothered gaze|Resilience against intimidation|Walk your path without hesitation")),
            Triple("unhurried", "Unhurried", Triple(0.70f, 0.18f, "Natural slow stride, spacious time|Rejection of artificial urgency|Pace yourself sustainably")),
            Triple("uncluttered", "Uncluttered", Triple(0.76f, 0.25f, "Clear field of perception|Clean cognitive slate|Enjoy mental spaciousness")),
            Triple("lucid", "Lucid Calm", Triple(0.80f, 0.35f, "Crystal clear awareness, no fog|Sharp perception in serenity|Direct this clarity to what matters")),
            Triple("halcyon", "Halcyon", Triple(0.82f, 0.18f, "Golden calm memory or state|Idyllic peace and flourishing|Store this state as an inner refuge")),
            Triple("restful", "Restful", Triple(0.70f, 0.15f, "Subtle breathing, limp muscles|Deep recovery mode|Rest without guilt")),
            Triple("cool", "Cool-headed", Triple(0.68f, 0.30f, "Lowered body heat, analytical focus|Rationality in heated context|Make the sound tactical decision")),
            Triple("balanced", "Balanced", Triple(0.76f, 0.28f, "Equal distribution of effort|Homeostasis of internal systems|Maintain moderation in all things")),
            Triple("composed_peace", "Composed Peace", Triple(0.78f, 0.22f, "Graceful stillness in posture|Mastery of peaceful demeanor|Lead others by calm example")),
            Triple("comforted", "Comforted", Triple(0.74f, 0.24f, "Warm shoulders, settled throat|Feeling held and supported|Rest in reassuring care")),
            Triple("aligned", "Aligned", Triple(0.80f, 0.30f, "Straight vertical spine, clear intent|Union of intention and action|Move forward in integrity")),
            Triple("immovable", "Immovable Peace", Triple(0.82f, 0.32f, "Rooted legs, undisturbed core|Mountain-like resilience|Let winds blow past you")),
            Triple("stillness", "Pure Stillness", Triple(0.85f, 0.08f, "Almost imperceptible pulse, stillness|Deep silence beyond words|Rest in pure awareness"))
        )
        for (item in calmData) {
            val parts = item.third.third.split("|")
            list.add(e(item.first, item.second, EmotionCategory.PEACE_CALM, item.third.first, item.third.second, parts[0], parts[1], parts[2]))
        }

        // 3. LOVE & CONNECTION (40 emotions)
        val loveData = listOf(
            Triple("loving", "Loving", Triple(0.88f, 0.5f, "Warm glowing chest, soft eyes|Deep relational bond and care|Express unconditional affection")),
            Triple("affectionate", "Affectionate", Triple(0.82f, 0.5f, "Reaching hands, gentle smile|Desire for physical/emotional warmth|Give a sincere hug or note")),
            Triple("tender", "Tender", Triple(0.80f, 0.4f, "Soft throat, delicate touch|Vulnerability and protective care|Handle delicate feelings gently")),
            Triple("compassionate", "Compassionate", Triple(0.85f, 0.45f, "Warm heart, empathic posture|Desire to alleviate suffering|Offer genuine support")),
            Triple("empathetic", "Empathetic", Triple(0.78f, 0.5f, "Mirroring breath, tuned attention|Direct attunement to another's state|Listen deeply without fixing")),
            Triple("caring", "Caring", Triple(0.80f, 0.45f, "Attentive gaze, leaning in|Investment in another's thriving|Take a proactive nurturing action")),
            Triple("devoted", "Devoted", Triple(0.85f, 0.6f, "Dedicated focus, steadfast posture|Loyalty to beloved persons or mission|Reaffirm your dedication")),
            Triple("adoring", "Adoring", Triple(0.90f, 0.65f, "Mesmerized smile, sparkling eyes|Celebration of beloved qualities|Express your heartfelt praise")),
            Triple("cherishing", "Cherishing", Triple(0.86f, 0.4f, "Gentle holding gesture, warm breath|Valuing what is precious and fragile|Protect and honor this bond")),
            Triple("intimate", "Intimate", Triple(0.84f, 0.5f, "Relaxed vulnerability, soft proximity|Sharing deep truth safely|Be present in mutual authenticity")),
            Triple("warm", "Warmhearted", Triple(0.78f, 0.35f, "Glowing torso, welcoming posture|Generosity of spirit|Offer hospitality and welcoming")),
            Triple("connected", "Connected", Triple(0.82f, 0.45f, "Synchronized breath, open heart|Interpersonal safety and union|Cultivate community ties")),
            Triple("fond", "Fond", Triple(0.75f, 0.35f, "Soft nostalgic smile, warm chest|Sweet remembrance and affection|Send a thinking-of-you message")),
            Triple("passionate", "Passionate", Triple(0.88f, 0.85f, "Surging pulse, intense gaze|Fierce romantic or creative devotion|Channel energy into creation")),
            Triple("enamored", "Enamored", Triple(0.85f, 0.7f, "Fluttering heart, focused attention|Fascination with a person or ideal|Enjoy the wondrous spark")),
            Triple("nurturing", "Nurturing", Triple(0.80f, 0.4f, "Open lap, comforting hands|Fostering growth in others|Feed and support someone")),
            Triple("accepting_love", "Unconditionally Loved", Triple(0.92f, 0.35f, "Full release of guarding tension|Feeling seen and embraced completely|Rest in being fully welcomed")),
            Triple("grateful_love", "Appreciative", Triple(0.84f, 0.45f, "Warm eyes, placed hand on heart|Recognizing relational gifts|Write a specific note of thanks")),
            Triple("bonded", "Bonded", Triple(0.82f, 0.45f, "Solid shoulder connection|Trust forged through shared trials|Stand beside your teammate")),
            Triple("sympathetic", "Sympathetic", Triple(0.72f, 0.4f, "Nodding head, moist eyes|Resonating with pain in others|Provide a safe listening ear")),
            Triple("attuned", "Attuned", Triple(0.78f, 0.45f, "Calm presence, open ears|Harmonizing with relational field|Respond with intuitive grace")),
            Triple("adoration", "Deep Adoration", Triple(0.88f, 0.6f, "Lifted hands, devotional breath|Honor of high virtue or beauty|Pay reverence to genuine good")),
            Triple("protective_love", "Fiercely Protective", Triple(0.80f, 0.75f, "Steadfast stance, watchful eyes|Guarding loved ones from harm|Set healthy boundaries for safety")),
            Triple("receptive_love", "Open-Hearted", Triple(0.84f, 0.4f, "Expansive ribs, soft chest center|Willingness to receive love and care|Let yourself be supported")),
            Triple("sisterly_brotherly", "Kinship", Triple(0.80f, 0.5f, "Easy laughter, shared understanding|Companionship of shared origin|Revisit shared memories")),
            Triple("allied", "Allied", Triple(0.76f, 0.55f, "Side-by-side stance, mutual respect|Cooperation toward shared aim|Support the common objective")),
            Triple("enchanted_love", "Smitten", Triple(0.86f, 0.75f, "Blushing cheeks, giddy laugh|Romantic delight and charm|Savor the delightful butterflies")),
            Triple("reverent_love", "Reverent", Triple(0.86f, 0.35f, "Solemn gratitude, bowed neck|Recognition of sacred relationship|Honor the sanctity of life")),
            Triple("forgiving", "Forgiving", Triple(0.80f, 0.35f, "Release of tight fists, deep breath|Letting go of resentment|Choose liberation over grudge")),
            Triple("hospitable", "Hospitable", Triple(0.78f, 0.45f, "Open doorway posture, serving hands|Making others feel at home|Welcome guests with warmth")),
            Triple("brotherhood", "Camaraderie", Triple(0.82f, 0.65f, "High five, shared grin, energetic shoulder|Joy of collective camaraderie|Celebrate team solidarity")),
            Triple("safe_love", "Securely Attached", Triple(0.88f, 0.25f, "Deep diaphragmatic breathing, ease|Knowing you are held and valued|Build securely from this home base")),
            Triple("yearning_love", "Pining Affection", Triple(0.65f, 0.6f, "Aching chest, searching eyes|Desire for reunion with beloved|Channel longing into poetry or call")),
            Triple("reunited", "Reunited Joy", Triple(0.92f, 0.8f, "Tight embrace, happy weeping|Restoration of broken separation|Hold closely and savor presence")),
            Triple("benevolent", "Benevolent", Triple(0.82f, 0.35f, "Gentle warm smile, generous posture|Desire to do good for all|Perform a quiet act of kindness")),
            Triple("charitable", "Charitable", Triple(0.80f, 0.45f, "Extended open palms|Sharing abundance with those in need|Give freely without expectation")),
            Triple("communal", "Communal", Triple(0.80f, 0.55f, "Gathered circle, shared food|Belonging to a tribe or family|Participate in group harmony")),
            Triple("tenderhearted", "Tenderhearted", Triple(0.82f, 0.38f, "Soft chest, responsive tear ducts|Pure emotional sensitivity|Honor your emotional depth")),
            Triple("magnetic", "Magnetic Connection", Triple(0.86f, 0.75f, "Drawn forward, tingling presence|Unmistakable relational resonance|Explore the mutual connection")),
            Triple("boundless_love", "Agape", Triple(0.95f, 0.5f, "Vast oceanic heart opening|Universal unconditional love|Radiate goodwill to all beings"))
        )
        for (item in loveData) {
            val parts = item.third.third.split("|")
            list.add(e(item.first, item.second, EmotionCategory.LOVE_CONNECTION, item.third.first, item.third.second, parts[0], parts[1], parts[2]))
        }

        // 4. VITALITY & DRIVE (40 emotions)
        val driveData = listOf(
            Triple("energized", "Energized", Triple(0.78f, 0.85f, "Tingling muscles, alert eyes|Readiness for high physical action|Tackle an ambitious challenge")),
            Triple("motivated", "Motivated", Triple(0.75f, 0.8f, "Forward-leaning spine, purposeful gaze|Goal-directed drive and intention|Start the most important task")),
            Triple("inspired", "Inspired", Triple(0.85f, 0.75f, "Expanded chest, sudden clarity|Inflow of creative insight|Write down the breakthrough idea")),
            Triple("determined", "Determined", Triple(0.70f, 0.8f, "Set jaw, focused brows, steady stance|Overcoming resistance with resolve|Maintain discipline on your course")),
            Triple("driven", "Driven", Triple(0.72f, 0.88f, "Pounding pulse, single-minded focus|Relentless pursuit of outcome|Break big goal into milestone 1")),
            Triple("empowered", "Empowered", Triple(0.82f, 0.75f, "Expanded posture, strong spine|Recognition of personal agency|Take ownership of your choices")),
            Triple("confident", "Confident", Triple(0.80f, 0.65f, "Level shoulders, steady eye contact|Faith in competence and resilience|Step forward without apology")),
            Triple("bold", "Bold", Triple(0.78f, 0.8f, "Direct posture, assertive stride|Willingness to take calculated risk|Make the bold ask or move")),
            Triple("courageous_drive", "Courageous", Triple(0.82f, 0.85f, "Active heartbeat, forward step|Acting despite fear|Let values lead over comfort")),
            Triple("ambitious", "Ambitious", Triple(0.74f, 0.82f, "Looking up, energized plans|Aspiration to reach higher potential|Map out the multi-year vision")),
            Triple("passionate_drive", "Fired Up", Triple(0.85f, 0.9f, "Hot palms, dynamic speech|High ignition of inner flame|Harness this fuel constructively")),
            Triple("invincible", "Invincible", Triple(0.88f, 0.95f, "Armored chest sensation, surge|Peak surge of self-efficacy|Remain grounded in humility")),
            Triple("dynamic", "Dynamic", Triple(0.78f, 0.8f, "Agile feet, quick adaptations|Capacity for rapid movement|Pivot smoothly to best solution")),
            Triple("unstoppable", "Unstoppable", Triple(0.82f, 0.92f, "Momentum in forward march|Breakthrough velocity over barriers|Keep moving through the gate")),
            Triple("resilient_drive", "Tenacious", Triple(0.75f, 0.78f, "Locked core, firm grip|Refusal to surrender under pressure|Hold the line and persevere")),
            Triple("enthused", "Enthused", Triple(0.80f, 0.78f, "Animated face, lively gestures|High engagement with work|Share excitement with team")),
            Triple("focused", "Laser-Focused", Triple(0.72f, 0.7f, "Tunnel vision, still body, sharp breath|Elimination of distractions|Complete single task uninterrupted")),
            Triple("productive", "Productive", Triple(0.76f, 0.65f, "Rhythmic keyboard/hand cadence|Efficient conversion of energy|Celebrate completed items")),
            Triple("zealous", "Zealous", Triple(0.75f, 0.88f, "Intense devotion, rapid speech|Ardent defense of mission|Check balance against burnout")),
            Triple("valiant", "Valiant", Triple(0.80f, 0.82f, "Nobility in posture, shield up|Heroic effort in difficult duty|Do the honorable deed")),
            Triple("audacious", "Audacious", Triple(0.76f, 0.85f, "Fearless grin, daring leap|Challenging conventional bounds|Test the innovative boundary")),
            Triple("resolute", "Resolute", Triple(0.75f, 0.75f, "Immovable feet, steady breath|Firm unwavering commitment|Stick to your agreed principles")),
            Triple("vital", "Vital", Triple(0.82f, 0.78f, "Glowing health, vibrant lungs|Peak biological energy flow|Honor your body with clean nutrition")),
            Triple("striving", "Striving", Triple(0.68f, 0.82f, "Muscles taut, reaching outward|Yearning for excellence|Praise the effort in process")),
            Triple("keen", "Keen", Triple(0.74f, 0.72f, "Sharp senses, leaning forward|Eager intellectual appetite|Investigate the new puzzle")),
            Triple("proactive", "Proactive", Triple(0.76f, 0.7f, "Anticipating moves, light feet|Acting ahead of future friction|Solve the problem before it grows")),
            Triple("adventurous", "Adventurous", Triple(0.82f, 0.8f, "Expansive gaze to horizon|Appetite for uncharted territory|Explore an unfamiliar route")),
            Triple("daring", "Daring", Triple(0.78f, 0.85f, "Adrenaline tickle in belly|Courage in face of uncertainty|Take the brave first step")),
            Triple("electric", "Electric", Triple(0.84f, 0.95f, "Tingling scalp and fingers|High neural ignition|Capture the burst on canvas/code")),
            Triple("catalytic", "Catalytic", Triple(0.80f, 0.85f, "Sparking energy, inspiring others|Triggering positive transformation|Ignite momentum in your circle")),
            Triple("assertive", "Assertive", Triple(0.72f, 0.65f, "Direct eye contact, clear voice|Standing up for fair boundaries|State your need with calm clarity")),
            Triple("purposeful", "Purposeful", Triple(0.78f, 0.68f, "Centered steps, clear direction|Meaningful alignment of daily task|Remember your larger 'Why'")),
            Triple("charged", "Fully Charged", Triple(0.82f, 0.9f, "Abundant stamina, ready spring|Full tank of bodily fuel|Channel power into physical workout")),
            Triple("ambitious_drive", "High-Aspiring", Triple(0.76f, 0.8f, "Gazing at peaks, resolute breath|Hunger for meaningful legacy|Build the enduring foundation")),
            Triple("creative_fire", "Creative Spark", Triple(0.86f, 0.82f, "Fast ideas, fluttering pulse|Connecting novel insights|Prototype immediately")),
            Triple("unstinting", "Tireless", Triple(0.74f, 0.8f, "Enduring stamina, steady rhythm|Generous exertion of strength|Take scheduled hydration breaks")),
            Triple("undaunted", "Undaunted", Triple(0.78f, 0.75f, "Unshaken posture after setback|Refusal to be intimidated by scale|Assess and resume with grit")),
            Triple("galvanized", "Galvanized", Triple(0.80f, 0.9f, "Sudden awakening to urgent call|Mobilization for collective action|Unite resources and deploy")),
            Triple("commanding", "Commanding", Triple(0.76f, 0.75f, "Authoritative voice, broad stance|Leadership in critical moments|Guide the team with calm direction")),
            Triple("unstoppable_fire", "Blazing Resolve", Triple(0.86f, 0.95f, "Heat in core, unstoppable motion|Indomitable will to manifest good|Transform resolve into reality"))
        )
        for (item in driveData) {
            val parts = item.third.third.split("|")
            list.add(e(item.first, item.second, EmotionCategory.VITALITY_DRIVE, item.third.first, item.third.second, parts[0], parts[1], parts[2]))
        }

        // 5. FEAR & ANXIETY (40 emotions)
        val fearData = listOf(
            Triple("anxious", "Anxious", Triple(-0.65f, 0.75f, "Tight chest, shallow breathing, racing thoughts|Anticipation of uncertain threat|Practice 4-7-8 physiological sigh")),
            Triple("fearful", "Fearful", Triple(-0.75f, 0.85f, "Rapid pulse, cold hands, widened eyes|Immediate survival threat response|Look around to verify current physical safety")),
            Triple("panicked", "Panicked", Triple(-0.90f, 0.98f, "Gasping breath, dizziness, urge to flee|Sympathetic system overdrive|Place feet flat on floor and hold cold object")),
            Triple("terrified", "Terrified", Triple(-0.95f, 0.95f, "Paralyzing chill, trembling limbs|Severe existential threat perception|Find a safe enclosed shelter immediately")),
            Triple("nervous", "Nervous", Triple(-0.45f, 0.65f, "Butterflies in stomach, fidgety hands|Mild social or performance concern|Warm your hands and stretch your neck")),
            Triple("worried", "Worried", Triple(-0.55f, 0.60f, "Furrowed brow, recurring mental loops|Preoccupation with negative outcomes|Write down what is within your control")),
            Triple("apprehensive", "Apprehensive", Triple(-0.50f, 0.65f, "Hesitant step, vigilant scanning|Anticipation of difficulty|Prepare a clear contingency plan")),
            Triple("dread", "Filled with Dread", Triple(-0.80f, 0.75f, "Heavy sinking pit in gut, cold sweat|Fearing an inevitable painful event|Face the root truth with a trusted friend")),
            Triple("uneasy", "Uneasy", Triple(-0.40f, 0.50f, "Vague internal discomfort, unsettled|Subconscious cue of incongruence|Pause and scan what feels out of alignment")),
            Triple("tense", "Tense", Triple(-0.50f, 0.70f, "Rigid neck, clenched shoulders|Muscular armoring for defense|Do progressive muscle relaxation")),
            Triple("alarmed", "Alarmed", Triple(-0.70f, 0.88f, "Startled jump, pounding heart|Sudden signal of imminent danger|Pause, verify the source before reacting")),
            Triple("jittery", "Jittery", Triple(-0.45f, 0.80f, "Shaky fingers, rapid speech|Excess adrenaline or stimulants|Drink water, walk, and eliminate caffeine")),
            Triple("restless_fear", "Fretful", Triple(-0.50f, 0.65f, "Pacing, picking at skin, sighing|Irritable anxiety and worry|Engage in soothing repetitive task")),
            Triple("insecure", "Insecure", Triple(-0.60f, 0.55f, "Hunched shoulders, averted gaze|Perception of inadequate safety|Reaffirm your inherent worth and safety")),
            Triple("vulnerable_fear", "Defenseless", Triple(-0.70f, 0.60f, "Exposed neck, trembling core|Absence of protective shield|Build your safe physical and emotional space")),
            Triple("hypervigilant", "Hypervigilant", Triple(-0.65f, 0.85f, "Darting eyes, sensitive hearing|Threat detection system on max|Soften gaze and listen to calming ambient sound")),
            Triple("distressed", "Distressed", Triple(-0.75f, 0.78f, "Tight throat, wringing hands|Acute emotional strain|Ask for immediate compassionate support")),
            Triple("edgy", "Edgy", Triple(-0.45f, 0.70f, "Sharp responses, jumpiness|Near-threshold irritation and fear|Step away from noisy environments")),
            Triple("suspicious", "Suspicious", Triple(-0.50f, 0.60f, "Narrowed eyes, guarded distance|Guarding against deceit|Seek verifiable facts before judging")),
            Triple("paranoid", "Paranoid", Triple(-0.80f, 0.85f, "Racing heart, secretive posture|Exaggerated perception of conspiracy|Ground in tangible neutral reality")),
            Triple("intimidated", "Intimidated", Triple(-0.65f, 0.65f, "Shrinking posture, quiet voice|Overwhelmed by power disparity|Remember your sovereign worth and rights")),
            Triple("horrified", "Horrified", Triple(-0.88f, 0.90f, "Gasp, frozen face, nausea|Shock at catastrophic occurrence|Seek grounding safety with companions")),
            Triple("phobic", "Phobic", Triple(-0.85f, 0.95f, "Urgent avoidance, hysterical pulse|Conditioned trigger avoidance|Remove trigger and breathe slowly")),
            Triple("flustered", "Flustered", Triple(-0.50f, 0.75f, "Flushed face, dropped items, stutter|Loss of composure under scrutiny|Stop, breathe, and slow down your pace")),
            Triple("rattled", "Rattled", Triple(-0.60f, 0.75f, "Trembling inside, disjointed speech|Shaken by unexpected disruption|Take 5 minutes of silent grounding")),
            Triple("frightened", "Frightened", Triple(-0.72f, 0.82f, "Wide pupils, pulled-back hands|Direct threat to safety|Move to a well-lit safe space")),
            Triple("petrified", "Petrified", Triple(-0.90f, 0.70f, "Frozen immobility, locked joints|Dorsal freeze response to fear|Gently wiggle toes and hum softly")),
            Triple("spooked", "Spooked", Triple(-0.55f, 0.78f, "Hair standing on neck, sudden turn|Sudden eerie stimulus|Rationalize the physical explanation")),
            Triple("cautious", "Overly Cautious", Triple(-0.35f, 0.45f, "Tiptoeing, delayed decisions|Preventing potential mistakes|Identify which risks are safe to take")),
            Triple("shaky", "Shaky", Triple(-0.55f, 0.70f, "Tremor in hands, unsteady knees|Adrenaline leaving the bloodstream|Eat a grounding snack with protein")),
            Triple("alarmed_fear", "Startled", Triple(-0.60f, 0.88f, "Sharp intake of air, jump|Reflexive sensory shock|Exhale slowly with a long sigh")),
            Triple("wary", "Wary", Triple(-0.40f, 0.50f, "Side-eye, guarded posture|Prudence in uncertain territory|Take measured, cautious steps")),
            Triple("skittish", "Skittish", Triple(-0.52f, 0.78f, "Ready to bolt at any sound|Heightened sensitivity in nervous system|Create a predictable quiet routine")),
            Triple("terrified_fear", "Terror-Stricken", Triple(-0.96f, 0.95f, "Inability to speak, pounding head|Overwhelming acute threat|Focus on holding a safe human's hand")),
            Triple("disquieted", "Disquieted", Triple(-0.48f, 0.45f, "Stirring in chest, unsettled mind|Loss of internal serenity|Journal the unspoken disturbance")),
            Triple("quivering", "Quivering", Triple(-0.62f, 0.68f, "Subtle shiver throughout torso|Release of pent-up panic|Wrap yourself in a warm heavy blanket")),
            Triple("aghast", "Aghast", Triple(-0.78f, 0.80f, "Dropped jaw, clutching throat|Dismay at sudden catastrophe|Focus on immediate next first aid step")),
            Triple("cowering", "Cowering", Triple(-0.85f, 0.60f, "Curled fetal posture, hands on head|Submission to overwhelming force|Find sanctuary where you are protected")),
            Triple("chilled", "Chilled with Fear", Triple(-0.75f, 0.65f, "Cold spine, goosebumps|Deep instinctual danger warning|Warm your body by a fire or tea")),
            Triple("existential_dread", "Existential Dread", Triple(-0.82f, 0.60f, "Hollow vastness in chest, vertigo|Confronting mortality or void|Anchor in present love and tangible acts"))
        )
        for (item in fearData) {
            val parts = item.third.third.split("|")
            list.add(e(item.first, item.second, EmotionCategory.FEAR_ANXIETY, item.third.first, item.third.second, parts[0], parts[1], parts[2]))
        }

        // 6. ANGRY & FRUSTRATED (40 emotions)
        val angerData = listOf(
            Triple("angry", "Angry", Triple(-0.70f, 0.85f, "Clenched jaw, heated face, tight fists|Mobilizing energy to defend boundary|State the violation clearly and cool down")),
            Triple("frustrated", "Frustrated", Triple(-0.60f, 0.75f, "Tension in forehead, irritated sighs|Blocked goal or repeated failure|Step back and try a different angle")),
            Triple("furious", "Furious", Triple(-0.90f, 0.95f, "Pounding temples, flared nostrils, shouting|Severe boundary violation or injustice|Disengage immediately to prevent harm")),
            Triple("irritated", "Irritated", Triple(-0.45f, 0.60f, "Restless jaw, eye rolling, curt tone|Low-grade friction or sensory grating|Remove the annoying stimulus")),
            Triple("annoyed", "Annoyed", Triple(-0.40f, 0.55f, "Tense lips, tapping foot|Minor disturbance to peace|Express preference politely")),
            Triple("enraged", "Enraged", Triple(-0.95f, 0.98f, "Hot blood in ears, trembling rage|Extreme rage at profound betrayal|Channel rage safely into vigorous workout")),
            Triple("indignant", "Indignant", Triple(-0.65f, 0.75f, "Upright spine, flared eyes, proud tone|Anger at unfairness or injustice|Advocate for structural fairness")),
            Triple("resentful", "Resentful", Triple(-0.70f, 0.50f, "Sour taste, tight throat, bitter brooding|Accumulated unexpressed grievances|Communicate unsaid needs honestly")),
            Triple("bitter", "Bitter", Triple(-0.75f, 0.40f, "Downward turned lips, cold gaze|Long-held grievance poisoning the heart|Grieve the unfair loss and let go")),
            Triple("hostile", "Hostile", Triple(-0.80f, 0.85f, "Glaring stare, aggressive posture|Readiness for combat or confrontation|Declare a temporary ceasefire")),
            Triple("agitated", "Agitated", Triple(-0.55f, 0.80f, "Pacing room, restless hands, sharp breath|Simmering nervous energy looking for fight|Go for a brisk 10-minute walk")),
            Triple("outraged", "Outraged", Triple(-0.85f, 0.90f, "Voice cracking with passion, burning chest|Moral fury at heinous act|Direct anger into lawful advocacy")),
            Triple("exasperated", "Exasperated", Triple(-0.65f, 0.70f, "Throwing hands up, deep sigh|Reaching the limit of patience|Take a 20-minute time-out")),
            Triple("infuriated", "Infuriated", Triple(-0.88f, 0.92f, "Shaking hands, laser focus on target|Reaching boiling point|Count down from 10 and drink ice water")),
            Triple("spiteful", "Spiteful", Triple(-0.75f, 0.65f, "Narrow smirk, vengeful thoughts|Desire to hurt back because of hurt|Heal your own wound rather than striking")),
            Triple("vengeful", "Vengeful", Triple(-0.82f, 0.80f, "Obsessive plotting, cold heart|Desire to exact retribution|Remember: justice is clean; revenge is poison")),
            Triple("irate", "Irate", Triple(-0.85f, 0.88f, "Flushed neck, booming voice|Extreme displeasure at bad service/breach|Demand resolution with firm poise")),
            Triple("choleric", "Hot-Tempered", Triple(-0.60f, 0.82f, "Instant heat rise, quick flare|Predisposition to rapid anger response|Learn your early bodily warning cues")),
            Triple("cranky", "Cranky", Triple(-0.45f, 0.50f, "Rubbing eyes, slumping, grumbling|Physical fatigue masquerading as anger|Take a nap and eat a nourishing meal")),
            Triple("grouchy", "Grouchy", Triple(-0.40f, 0.45f, "Scowling brow, short answers|Sour mood from poor sleep/discomfort|Get fresh air and gentle quiet")),
            Triple("testy", "Testy", Triple(-0.50f, 0.65f, "Snapping at questions, thin skin|Low resilience to everyday interruptions|Ask for space while you finish your task")),
            Triple("peeved", "Peeved", Triple(-0.35f, 0.50f, "Puffed cheeks, muttering|Mild grudge at petty slight|Let the small stuff slide")),
            Triple("incensed", "Incensed", Triple(-0.82f, 0.85f, "Burning throat, rigid posture|Fuming with intense displeasure|Write an unsent letter to purge the venom")),
            Triple("fuming", "Fuming", Triple(-0.78f, 0.80f, "Steam rising sensation in ears|Suppressed anger looking to vent|Vent safely to an objective counselor")),
            Triple("livid", "Livid", Triple(-0.92f, 0.92f, "Pale with fury, piercing whisper|Ice-cold dangerous rage|Do not make permanent choices today")),
            Triple("cantankerous", "Cantankerous", Triple(-0.50f, 0.55f, "Stubborn resistance, scoffing|Chronic irritability and disputation|Practice saying 'Yes, and' once today")),
            Triple("antagonistic", "Antagonistic", Triple(-0.65f, 0.75f, "Mocking posture, picking fights|Projecting internal pain outward|Look at what part of you feels threatened")),
            Triple("cross", "Cross", Triple(-0.40f, 0.50f, "Tightly crossed arms, pursed mouth|Displeasure with someone's conduct|Clarify expectations kindly")),
            Triple("seething", "Seething", Triple(-0.80f, 0.75f, "Clenched teeth, silent vibrating heat|Internalized fury bubbling under lid|Release jaw tension with slow yawns")),
            Triple("quarrelsome", "Quarrelsome", Triple(-0.55f, 0.70f, "Argumentative tone, interrupting|Need to prove superiority or control|Step back from the debate")),
            Triple("sullen", "Sullen Anger", Triple(-0.60f, 0.35f, "Heavy silence, refusal to look|Passive aggressive brooding|Express the true hurt behind the wall")),
            Triple("displeased", "Displeased", Triple(-0.45f, 0.40f, "Slight head shake, flat tone|Standard not being met|Provide constructive feedback")),
            Triple("chafing", "Chafing", Triple(-0.50f, 0.65f, "Restlessness against restraints|Resentment against unwanted rule|Channel energy into creative autonomy")),
            Triple("wrathful", "Wrathful", Triple(-0.94f, 0.96f, "Thunderous presence, fierce force|Desire for total destruction of obstacle|Pause and seek cooler counsel")),
            Triple("petulant", "Petulant", Triple(-0.52f, 0.60f, "Pouting lips, stamping foot|Immature reaction to not getting way|Accept reality's constraints with maturity")),
            Triple("scornful", "Scornful", Triple(-0.70f, 0.60f, "Curled lip, derisive chuckle|Viewing target with disdain|Treat others with universal baseline dignity")),
            Triple("belligerent", "Belligerent", Triple(-0.80f, 0.88f, "Chest puffed, aggressive posturing|Eager for hostile brawl|Disarm by lowering your volume")),
            Triple("touchy", "Touchy", Triple(-0.48f, 0.68f, "Defensive flinch, rapid retort|Hypersensitivity around tender wound|Protect your tender spots with care")),
            Triple("umbrage", "Taking Umbrage", Triple(-0.62f, 0.70f, "Stiff neck, affronted pride|Feeling offended in honor|Check if ego is over-reacting")),
            Triple("raging", "Raging", Triple(-0.92f, 0.98f, "Roaring breath, surging adrenaline|Full-body mobilization for war|Scream into a pillow and release safely"))
        )
        for (item in angerData) {
            val parts = item.third.third.split("|")
            list.add(e(item.first, item.second, EmotionCategory.ANGER_FRUSTRATION, item.third.first, item.third.second, parts[0], parts[1], parts[2]))
        }

        // 7. SAD & GRIEVING (40 emotions)
        val sadData = listOf(
            Triple("sad", "Sad", Triple(-0.70f, 0.30f, "Drooping posture, lump in throat, tears|Processing loss, disappointment, or transition|Allow tears to release natural endorphins")),
            Triple("grieving", "Grieving", Triple(-0.85f, 0.45f, "Heavy heart, waves of sorrow, fatigue|Mourning the death or loss of beloved|Honor the grief with sacred space")),
            Triple("sorrowful", "Sorrowful", Triple(-0.80f, 0.35f, "Aching chest, slow heavy breath|Deep mourning of tragedy|Let the wave of sorrow wash through")),
            Triple("heartbroken", "Heartbroken", Triple(-0.92f, 0.50f, "Physical pain in center of chest, sobs|Torn attachment, profound grief|Hold yourself with tender kindness")),
            Triple("mournful", "Mournful", Triple(-0.80f, 0.25f, "Slow cadence, lowered head|Honoring the sacredness of what passed|Light a candle in commemoration")),
            Triple("depressed", "Depressed", Triple(-0.88f, 0.15f, "Lead-heavy limbs, flat affect, low drive|System shutdown to conserve vital resources|Do one tiny self-care act: drink water")),
            Triple("melancholic", "Melancholic", Triple(-0.55f, 0.25f, "Sweet bittersweet sadness, wistful gaze|Philosophical reflection on impermanence|Listen to evocative classical music")),
            Triple("despondent", "Despondent", Triple(-0.85f, 0.20f, "Slumped spine, hopeless sigh|Loss of belief in recovery|Reach out to one caring human")),
            Triple("gloomy", "Gloomy", Triple(-0.50f, 0.25f, "Dark mental sky, slow gait|Overcast outlook on life|Step out into natural sunshine")),
            Triple("dejected", "Dejected", Triple(-0.65f, 0.30f, "Cast-down eyes, slumped shoulders|Rejection and loss of status/hope|Remember rejection is redirection")),
            Triple("disheartened", "Disheartened", Triple(-0.60f, 0.35f, "Loss of spark in chest, heavy breath|Discouragement after sustained effort|Rest before re-evaluating")),
            Triple("hopeless", "Hopeless", Triple(-0.90f, 0.15f, "Hollow stomach, complete resignation|Perception of zero viable pathways|Borrow hope from someone who believes in you")),
            Triple("lonely", "Lonely", Triple(-0.72f, 0.35f, "Cold empty chest, aching solitude|Need for social belonging and attunement|Reach out for genuine human connection")),
            Triple("isolated", "Isolated", Triple(-0.75f, 0.25f, "Feeling behind thick glass, numbness|Disconnection from tribe/world|Join a community group or circle")),
            Triple("abandoned", "Abandoned", Triple(-0.90f, 0.55f, "Cold gut, desperate weeping|Primal terror of being left alone|Be the loving adult who never leaves you")),
            Triple("forlorn", "Forlorn", Triple(-0.78f, 0.20f, "Wandering gaze, slow steps|Feeling pitifully lonely and desolate|Wrap in a warm blanket and rest")),
            Triple("bleak", "Bleak", Triple(-0.75f, 0.15f, "Grey perception, flat voice|Sterile outlook on future|Find one tiny spot of color")),
            Triple("crestfallen", "Crestfallen", Triple(-0.65f, 0.30f, "Fallen face after high expectation|Disappointment crashing on hopes|Give yourself grace to recover")),
            Triple("anguished", "Anguished", Triple(-0.92f, 0.70f, "Torn sobs, clutching chest|Excruciating emotional agony|Place hand over heart and breathe slowly")),
            Triple("woeful", "Woeful", Triple(-0.74f, 0.30f, "Downcast countenance, weary sighs|Accumulated sorrow and burden|Share the burden with a helper")),
            Triple("wistful", "Wistful", Triple(-0.40f, 0.30f, "Soft distant stare, faint smile|Yearning for what once was|Honor the beauty of past chapters")),
            Triple("homesick", "Homesick", Triple(-0.65f, 0.40f, "Ache in chest, longing for home|Need for familiar safety and roots|Cook a nostalgic comfort meal")),
            Triple("bereaved", "Bereaved", Triple(-0.88f, 0.30f, "Heavy mourning, dark veil of grief|Deep loss of primary person|Take life one single breath at a time")),
            Triple("crushed", "Crushed", Triple(-0.85f, 0.35f, "Collapsed posture, breathless ache|Shattered expectations or betrayal|Allow yourself to be gently pieced back")),
            Triple("somber", "Somber", Triple(-0.55f, 0.20f, "Solemn face, quiet dignified tone|Respectful gravity of serious occasion|Honor the weight of the moment")),
            Triple("downcast", "Downcast", Triple(-0.58f, 0.25f, "Eyes glued to floor, slow nodding|Feeling low and diminished|Gently lift your chin to look at sky")),
            Triple("weepy", "Weepy", Triple(-0.68f, 0.45f, "Watery eyes, trembling chin|Emotional threshold close to tears|Let the cleansing tears fall")),
            Triple("inconsolable", "Inconsolable", Triple(-0.95f, 0.65f, "Racking sobs, rejecting words|Grief too deep for immediate logic|Simply hold physical presence in silence")),
            Triple("disconsolate", "Disconsolate", Triple(-0.80f, 0.25f, "Unresponsive to cheer, withdrawn|Unable to find comfort yet|Allow time to soften the edge")),
            Triple("lamenting", "Lamenting", Triple(-0.76f, 0.45f, "Crying out sorrow, expressive grief|Giving voice to profound loss|Write a lament or eulogy")),
            Triple("heavyhearted", "Heavyhearted", Triple(-0.70f, 0.25f, "Heavy weight on sternum|Carrying painful knowledge|Share the heavy load with a confidant")),
            Triple("pensive_sad", "Pensive Sadness", Triple(-0.45f, 0.20f, "Chin in hand, slow thoughts|Thoughtful sorrow uncovering truth|Journal your insights")),
            Triple("defeated", "Defeated", Triple(-0.80f, 0.18f, "Slumped in chair, hands open in surrender|Exhaustion after losing battle|Surrender the fight; save your life")),
            Triple("alienated", "Alienated", Triple(-0.75f, 0.35f, "Feeling foreign in room, cold skin|Estrangement from surroundings|Find one soul who speaks your language")),
            Triple("lovesick", "Lovesick", Triple(-0.60f, 0.50f, "Pining heart, loss of appetite|Sorrow of unrequited or distant love|Redirect affection into self-love")),
            Triple("morose", "Morose", Triple(-0.65f, 0.22f, "Sullen withdrawal, bitter sighs|Ill-tempered melancholy|Engage in physical movement")),
            Triple("regretful_sad", "Sorrowful Regret", Triple(-0.70f, 0.35f, "Aching forehead, wishing to undo|Grief over past mistake|Make amends where possible, then forgive")),
            Triple("hollow", "Hollow", Triple(-0.80f, 0.10f, "Empty cavern in torso, numbness|Depletion of emotional substance|Fill with slow breaths and warm soup")),
            Triple("resigned", "Resigned Sorrow", Triple(-0.65f, 0.15f, "Shrug of shoulders, flat gaze|Accepting sad inevitable outcome|Look for peace within the reality")),
            Triple("desolate", "Desolate", Triple(-0.92f, 0.18f, "Vast empty desert in chest|Feeling totally abandoned by life|Remember: dawn always follows the night"))
        )
        for (item in sadData) {
            val parts = item.third.third.split("|")
            list.add(e(item.first, item.second, EmotionCategory.SADNESS_GRIEF, item.third.first, item.third.second, parts[0], parts[1], parts[2]))
        }

        // 8. DISGUST & AVERSION (40 emotions)
        val disgustData = listOf(
            Triple("disgusted", "Disgusted", Triple(-0.70f, 0.60f, "Wrinkled nose, curled lip, churning stomach|Aversion to toxic or foul stimuli|Create immediate physical distance")),
            Triple("repulsed", "Repulsed", Triple(-0.80f, 0.70f, "Recoiling torso, pushing hands outward|Intense urge to push something away|Set uncompromising personal boundary")),
            Triple("revolted", "Revolted", Triple(-0.85f, 0.75f, "Gag reflex, shivering aversion|Extreme physical or moral revulsion|Cleanse your sensory palette with fresh air")),
            Triple("appalled", "Appalled", Triple(-0.75f, 0.65f, "Covered mouth, stiff shocked posture|Horrified dismay at unethical conduct|Document the breach and voice dissent")),
            Triple("averse", "Averse", Triple(-0.45f, 0.40f, "Turned cheek, defensive step back|Strong reluctance and disinclination|Honor your hesitation; do not force it")),
            Triple("contemptuous", "Contemptuous", Triple(-0.65f, 0.50f, "One-sided sneer, dismissive wave|Viewing target as beneath dignity|Examine what wounded boundary contempt hides")),
            Triple("distaste", "Distaste", Triple(-0.40f, 0.35f, "Pursed lips, slow blink|Dislike of aesthetic or behavioral quality|Choose an environment aligned with taste")),
            Triple("nauseated", "Nauseated", Triple(-0.80f, 0.50f, "Queasy stomach, tight throat|Physical or emotional sickness|Sip peppermint tea and rest in fresh breeze")),
            Triple("squeamish", "Squeamish", Triple(-0.50f, 0.60f, "Squinting eyes, tensed gut|Easily unsettled by unpleasant sights|Redirect focus to calming textures")),
            Triple("disdainful", "Disdainful", Triple(-0.60f, 0.45f, "Arched eyebrow, aloof posture|Scornful attitude toward bad conduct|Channel discernment constructively")),
            Triple("sickened", "Sickened", Triple(-0.85f, 0.65f, "Hollow stomach, lowered head|Profound moral or emotional disturbance|Engage in restorative grounding")),
            Triple("offended", "Offended", Triple(-0.60f, 0.60f, "Straightened neck, indignant stare|Resentment from perceived slight|Clarify your standards with calm composure")),
            Triple("abhorrent", "Abhorring", Triple(-0.88f, 0.75f, "Shivering recoil, total rejection|Deep moral hatred of cruelty|Stand against cruelty with dignity")),
            Triple("loathing", "Loathing", Triple(-0.85f, 0.70f, "Intense venomous stare, disgust|Deep seated detestation|Sever toxic entanglements")),
            Triple("detesting", "Detesting", Triple(-0.78f, 0.65f, "Shaking head, bitter rejection|Intense dislike and disapproval|Direct energy away from what you detest")),
            Triple("scandalized", "Scandalized", Triple(-0.70f, 0.75f, "Gasps, wide eyes, hand on heart|Shocked offense at social violation|Evaluate the truth of the claim")),
            Triple("displeased_disgust", "Turned Off", Triple(-0.50f, 0.45f, "Pulling back interest, coldness|Loss of attraction or willingness|Politely decline further engagement")),
            Triple("grossed_out", "Grossed Out", Triple(-0.65f, 0.60f, "Wiping tongue, shuddering shoulders|Direct visceral disgust|Wash hands and step away")),
            Triple("repugnant", "Repugned", Triple(-0.82f, 0.70f, "Visceral shiver, curling lips|Deep incompatibility with ethics/nature|Refuse to participate")),
            Triple("disturbed", "Disturbed", Triple(-0.68f, 0.60f, "Unsettled thoughts, furrowed brow|Cognitive or sensory dissonance|Return to safe and wholesome routines")),
            Triple("unclean", "Sensory Impurity", Triple(-0.60f, 0.55f, "Urge to shower or scrub skin|Perception of contamination|Take a cleansing hot shower")),
            Triple("sour", "Sour", Triple(-0.45f, 0.35f, "Puckered mouth, cynical thoughts|Bitter distaste with situation|Clear your palate with humor")),
            Triple("skeptical_disgust", "Wary Distaste", Triple(-0.40f, 0.40f, "Squinting eye, suspicious posture|Distrust of fraudulent promises|Demand verifiable integrity")),
            Triple("cynical", "Cynical", Triple(-0.55f, 0.35f, "Sardonic smirk, dry remark|Armor of disbelief against betrayal|Seek out genuine goodness to restore faith")),
            Triple("disapproving", "Disapproving", Triple(-0.48f, 0.45f, "Crossed arms, stern countenance|Moral or technical judgment|State constructive expectations")),
            Triple("scoffing", "Scoffing", Triple(-0.58f, 0.55f, "Derisive snort, dismissal|Rejection of foolishness|Explain the rational flaw calmly")),
            Triple("alienated_disgust", "Repelled", Triple(-0.75f, 0.65f, "Forced backward step, barrier up|Instinctive rejection of threat|Walk away cleanly")),
            Triple("unpalatable", "Unpalatable", Triple(-0.45f, 0.30f, "Refusal to swallow or agree|Inability to accept proposed terms|Propose an acceptable alternative")),
            Triple("allergic", "Emotionally Allergic", Triple(-0.60f, 0.70f, "Immediate irritation and sneeze/flare|Low tolerance for specific trigger|Establish a no-contact rule")),
            Triple("defiled", "Defiled", Triple(-0.85f, 0.55f, "Deep grief and disgust in skin|Violation of sacred personal space|Engage in a sacred purification ritual")),
            Triple("antipathy", "Antipathy", Triple(-0.65f, 0.50f, "Natural opposition and dislike|Deep natural friction with personality|Maintain polite professional distance")),
            Triple("condescending", "Condescending", Triple(-0.50f, 0.45f, "Looking down nose, patronizing tone|False superiority over another|Practice seeing common humanity")),
            Triple("distasteful", "Distasteful", Triple(-0.52f, 0.38f, "Grimace, averted ears|Aesthetic offense at vulgarity|Seek uplifting refined art")),
            Triple("reprehensible", "Reprehensible", Triple(-0.80f, 0.70f, "Stern point, absolute rejection|Condemnation of wrongful act|Hold wrongdoing accountable")),
            Triple("bauseous_revolt", "Visceral Revolt", Triple(-0.88f, 0.75f, "Dry heaving, cold shiver|Total nervous rejection|Exit the environment immediately")),
            Triple("prudish", "Prudish Aversion", Triple(-0.40f, 0.50f, "Blushing flinch, covered eyes|Fear of moral exposure|Examine internal conditioning")),
            Triple("intolerant", "Intolerant", Triple(-0.55f, 0.65f, "Clenched teeth, refusal to listen|Zero bandwidth for nonsense|Breathe and choose battles wisely")),
            Triple("nauseous_disdain", "Nauseous Disdain", Triple(-0.78f, 0.55f, "Slight gag, turned back|Rejection of fraudulent hypocrites|Protect your integrity")),
            Triple("grudging", "Grudging", Triple(-0.45f, 0.40f, "Reluctant nod, dragging feet|Yielding under protest|Clarify terms of compromise")),
            Triple("purging", "Purging Aversion", Triple(-0.70f, 0.70f, "Deep exhalation, cleaning frenzy|Urge to rid life of toxicity|Declutter and clean your living space"))
        )
        for (item in disgustData) {
            val parts = item.third.third.split("|")
            list.add(e(item.first, item.second, EmotionCategory.DISGUST_AVERSION, item.third.first, item.third.second, parts[0], parts[1], parts[2]))
        }

        // 9. SURPRISE & WONDER (40 emotions)
        val aweData = listOf(
            Triple("surprised", "Surprised", Triple(0.60f, 0.80f, "Raised eyebrows, wide eyes, quick breath|Alerting system to unexpected novelty|Orient attention to the new data")),
            Triple("astonished", "Astonished", Triple(0.75f, 0.85f, "Open mouth, frozen gaze, gasping breath|Overwhelming breakthrough of surprise|Give your brain a moment to integrate")),
            Triple("amazed", "Amazed", Triple(0.85f, 0.80f, "Smiling disbelief, shaking head in joy|Delight at unexpected greatness|Applaud the remarkable achievement")),
            Triple("awed", "In Awe", Triple(0.90f, 0.70f, "Goosebumps, vastness in chest, quiet|Encountering vastness beyond comprehension|Surrender to the sublime beauty")),
            Triple("curious", "Curious", Triple(0.70f, 0.60f, "Tilted head, focused pupils, leaning in|Drive to explore and acquire knowledge|Ask an open-ended investigative question")),
            Triple("wonder", "Filled with Wonder", Triple(0.88f, 0.65f, "Childlike sparkling eyes, soft smile|Appreciation of mystery and beauty|Observe the natural world closely")),
            Triple("fascinated", "Fascinated", Triple(0.80f, 0.70f, "Deep absorption, forgetfulness of time|Intense intellectual magnetic pull|Dive deeper into research")),
            Triple("intrigued", "Intrigued", Triple(0.65f, 0.60f, "Narrowed perceptive eyes, stroke chin|Piqued interest in subtle anomaly|Follow the curiosity breadcrumbs")),
            Triple("mystified", "Mystified", Triple(0.40f, 0.65f, "Scratching head, furrowed puzzle brow|Puzzled by unexplainable phenomenon|Embrace the joy of not knowing")),
            Triple("spellbound", "Spellbound", Triple(0.85f, 0.60f, "Motionless breath, captivated focus|Enchanted by masterful performance|Enjoy the artistic immersion")),
            Triple("flabbergasted", "Flabbergasted", Triple(0.30f, 0.88f, "Speechless, blinking rapidly|Complete disbelief at shocking facts|Verify details before drawing conclusions")),
            Triple("astounded", "Astounded", Triple(0.80f, 0.85f, "Hands on cheeks, wide grin|Stunned by extraordinary feat|Express your admiration")),
            Triple("mesmerized", "Mesmerized", Triple(0.82f, 0.50f, "Glazed wonder, rhythmic swaying|Hypnotic absorption in beauty|Stay present in the visual melody")),
            Triple("beguiled", "Beguiled", Triple(0.65f, 0.60f, "Charmed smile, enchanted attention|Fascinated by allure or wit|Keep your critical awareness active")),
            Triple("inquisitive", "Inquisitive", Triple(0.68f, 0.65f, "Perked ears, energetic questioning|Appetite for foundational truth|Probe beneath the surface")),
            Triple("enthralled", "Enthralled", Triple(0.86f, 0.75f, "Rapt attention, leaning over seat|Complete capture by gripping narrative|Follow the story to its climax")),
            Triple("mindblown", "Mind-Blown", Triple(0.85f, 0.90f, "Expanding scalp sensation, wide grin|Paradigm shift in understanding|Rewrite your mental model")),
            Triple("speechless", "Speechless", Triple(0.60f, 0.65f, "Caught breath, silent throat|Words inadequate for reality|Let quiet silence speak")),
            Triple("stunned", "Stunned", Triple(0.35f, 0.80f, "Static posture, paused breath|Sudden collision with surprising news|Sit down and take a deep breath")),
            Triple("captivated", "Captivated", Triple(0.84f, 0.65f, "Unbroken eye contact, soft smile|Heart held by captivating beauty|Give your full presence")),
            Triple("startled_awe", "Pleasantly Startled", Triple(0.70f, 0.80f, "Quick jump, followed by laughter|Harmless pleasant surprise|Laugh at the unexpected twist")),
            Triple("electrified", "Electrified", Triple(0.88f, 0.92f, "Hair on arms standing, spark in eyes|Sudden surge of awe and energy|Channel this current into action")),
            Triple("illuminated", "Illuminated", Triple(0.82f, 0.60f, "Glow in eyes, relaxed clarity|Sudden dawn of profound truth|Write down the core revelation")),
            Triple("bewildered_wonder", "Bewildered Wonder", Triple(0.55f, 0.65f, "Tilted brow, marveling gaze|Marveling at complex machinery|Study how the parts interlock")),
            Triple("humbled_awe", "Humbled by Scale", Triple(0.85f, 0.40f, "Smallness sensation under stars|Realization of personal scale in cosmos|Rejoice in being part of the vast universe")),
            Triple("transfixed", "Transfixed", Triple(0.78f, 0.55f, "Rooted to the spot, silent awe|Hypnotic reverence|Take in every fine detail")),
            Triple("dazzled", "Dazzled", Triple(0.82f, 0.75f, "Shielding eyes with smile, sparkling|Overwhelmed by brilliant radiance|Appreciate the brilliant display")),
            Triple("struck", "Thunderstruck", Triple(0.60f, 0.85f, "Vibrating core, wide open stare|Struck by magnitude of truth|Respect the sudden revelation")),
            Triple("marveling", "Marveling", Triple(0.85f, 0.55f, "Sigh of appreciation, quiet gaze|Contemplating human/natural genius|Share your admiration with others")),
            Triple("reverent_wonder", "Sacred Wonder", Triple(0.92f, 0.45f, "Bowed head, hand over chest|Touching the holy mystery of life|Offer quiet gratitude")),
            Triple("inquisitive_drive", "Philosophical Wonder", Triple(0.75f, 0.55f, "Gazing at clouds, deep breaths|Seeking meaning of existence|Explore deep questions")),
            Triple("exploratory", "Exploratory", Triple(0.76f, 0.70f, "Brisk stride into new spaces|Thirst to map new domains|Step off the beaten path")),
            Triple("awakened", "Awakened", Triple(0.84f, 0.75f, "Rubbing eyes, crystal sharp focus|Seeing reality anew without illusion|Embrace this fresh perspective")),
            Triple("breathless", "Breathless", Triple(0.80f, 0.80f, "Suspended inhale, marveling|Taken away by breathtaking majesty|Exhale slowly and take it in")),
            Triple("staggered", "Staggered", Triple(0.65f, 0.85f, "Step backward in disbelief|Impact of colossal revelation|Take time to assimilate")),
            Triple("wonderstruck", "Wonderstruck", Triple(0.90f, 0.78f, "Gasp, sparkling tears of joy|Direct contact with magic of nature|Preserve this childlike marvel")),
            Triple("disoriented_wonder", "Cosmic Vertigo", Triple(0.70f, 0.65f, "Light spinning, profound expansion|Shifting from local to cosmic view|Feel your feet on planet Earth")),
            Triple("keen_wonder", "Keen Intrigue", Triple(0.72f, 0.65f, "Intense focus on anomaly|Discovery of hidden secret|Unpack the clue")),
            Triple("radiant_awe", "Radiant Awe", Triple(0.92f, 0.75f, "Warm glowing heart, vast smile|Overwhelmed by boundless good|Radiate that wonder to all")),
            Triple("sublime", "Sublime", Triple(0.95f, 0.65f, "Spine tingling, profound hush|Touching terrifying and gorgeous reality|Stand in sacred silence"))
        )
        for (item in aweData) {
            val parts = item.third.third.split("|")
            list.add(e(item.first, item.second, EmotionCategory.AWE_CURIOSITY, item.third.first, item.third.second, parts[0], parts[1], parts[2]))
        }

        // 10. OVERWHELM & FATIGUE (40 emotions)
        val fatigueData = listOf(
            Triple("exhausted", "Exhausted", Triple(-0.70f, 0.15f, "Heavy eyelids, limp limbs, slow pulse|Biological battery depleted|Sleep and rest without apology")),
            Triple("overwhelmed", "Overwhelmed", Triple(-0.75f, 0.80f, "Racing pulse, drowning sensation, brain fog|Inputs exceeding cognitive capacity|Stop all inputs and do 1 single priority")),
            Triple("burned_out", "Burned Out", Triple(-0.85f, 0.15f, "Cynicism, emotional numbness, exhaustion|Chronic mismatch of demands and recharge|Initiate radical boundary and recovery protocol")),
            Triple("fatigued", "Fatigued", Triple(-0.60f, 0.20f, "Yawning, heavy muscles, lagging step|Accumulated physical exertion|Hydrate, eat protein, and sleep")),
            Triple("drained", "Drained", Triple(-0.65f, 0.18f, "Hollow sensation, zero emotional reserve|Giving out more than taking in|Replenish your inner well")),
            Triple("depleted", "Depleted", Triple(-0.75f, 0.12f, "Shaky weakness, cold extremities|Running on empty fumes|Refuel with deep rest and nutrition")),
            Triple("weary", "Weary", Triple(-0.60f, 0.22f, "Sighing deeply, dragging feet|Tiredness of soul from long struggle|Lay down your heavy burden for tonight")),
            Triple("lethargic", "Lethargic", Triple(-0.50f, 0.10f, "Slow reactions, stuck to couch|Low dopamine and arousal state|Get 5 minutes of sunlight and cool water")),
            Triple("sluggish", "Sluggish", Triple(-0.45f, 0.15f, "Heavy digestive feeling, slow brain|Metabolic or mental lag|Do gentle stretching and drink water")),
            Triple("spent", "Spent", Triple(-0.70f, 0.10f, "Empty hands, blank mind|Full expenditure of today's energy|Celebrate the effort, now rest")),
            Triple("taxed", "Taxed", Triple(-0.55f, 0.45f, "Tight temples, cognitive strain|Heavy cognitive processing load|Close screens for 15 minutes")),
            Triple("frazzled", "Frazzled", Triple(-0.65f, 0.75f, "Jangled nerves, dropped keys, stutter|Multiple competing urgent demands|Turn off phone notifications")),
            Triple("scattered", "Scattered", Triple(-0.50f, 0.65f, "Eyes darting, jumping between tabs|Attention fragmentation|Write 3 bullet points on paper")),
            Triple("brain_fried", "Brain Fried", Triple(-0.60f, 0.25f, "Headache, unable to formulate words|Synaptic saturation from overwork|Rest your eyes in darkness")),
            Triple("overstimulated", "Overstimulated", Triple(-0.65f, 0.85f, "Sound too loud, light too bright|Sensory processing overload|Put on noise-canceling headphones")),
            Triple("stretched_thin", "Stretched Thin", Triple(-0.68f, 0.60f, "Tension in neck, rushing between duties|Overcommitted schedule|Politely cancel non-essential commitments")),
            Triple("burdened", "Burdened", Triple(-0.70f, 0.35f, "Heavy shoulders, stooped back|Carrying excessive responsibility|Delegate or drop unnecessary loads")),
            Triple("numb_fatigue", "Numbed Out", Triple(-0.60f, 0.08f, "Absence of sensation, staring at wall|Dissociative defense against overload|Touch ice, smell mint, ground physically")),
            Triple("worn_out", "Worn Out", Triple(-0.65f, 0.15f, "Frayed energy, weak grip|End of endurance cycle|Grant yourself full recuperation")),
            Triple("sleepy", "Sleepy", Triple(-0.30f, 0.12f, "Heavy head, drooping lids, yawn|Circadian urge for sleep|Go to bed now")),
            Triple("somnolent", "Somnolent", Triple(-0.35f, 0.08f, "Dreamy drowsiness, drifting off|Brain entering theta/delta waves|Let yourself drift off to sleep")),
            Triple("overworked", "Overworked", Triple(-0.72f, 0.50f, "Chronic backache, blurry vision|Systemic lack of work-life balance|Set hard cut-off time for work")),
            Triple("dead_tired", "Dead Tired", Triple(-0.80f, 0.05f, "Collapsing onto bed, instant drop|Extreme physical exhaustion|Sleep for 8-9 hours uninterrupted")),
            Triple("unfocused_fatigue", "Zoned Out", Triple(-0.40f, 0.10f, "Staring into space, missed words|Attention mechanism resting|Allow the brief daydream")),
            Triple("encumbered", "Encumbered", Triple(-0.55f, 0.35f, "Sluggish movement under baggage|Dragging too much emotional weight|Unload what is not yours")),
            Triple("congested_mind", "Mentally Congested", Triple(-0.50f, 0.40f, "Pressure behind forehead|Need to clear cache of thoughts|Do a 10-minute brain dump on journal")),
            Triple("bedraggled", "Bedraggled", Triple(-0.62f, 0.25f, "Disheveled look, dragging feet|Weathering intense storm of duties|Take a warm bath and clean clothes")),
            Triple("prostrated", "Prostrated", Triple(-0.85f, 0.08f, "Lying flat, unable to rise|Total physical breakdown|Complete rest under medical/care support")),
            Triple("stale", "Stale", Triple(-0.45f, 0.20f, "Flat routine, lack of novelty|Need for creative cross-pollination|Change your physical scenery")),
            Triple("languid", "Languid", Triple(-0.25f, 0.15f, "Slow languorous movement|Low energy without acute pain|Enjoy an unhurried Sunday pace")),
            Triple("enervated", "Enervated", Triple(-0.65f, 0.15f, "Loss of nerve force and drive|Vitality sapped by environment|Change the toxic climate")),
            Triple("fatigued_soul", "World-Weary", Triple(-0.70f, 0.20f, "Sighing at news, heavy heart|Weariness from human folly|Disconnect from social media")),
            Triple("overextended", "Overextended", Triple(-0.60f, 0.65f, "Racing the clock, missed deadlines|Saying yes when needing to say no|Say no to all new requests")),
            Triple("haggard", "Haggard", Triple(-0.72f, 0.25f, "Dark eye circles, drawn cheeks|Long-term stress taking physical toll|Prioritize sleep, hydration, and nutrition")),
            Triple("paralyzed_overwhelm", "Executive Freeze", Triple(-0.75f, 0.50f, "Staring at to-do list, unable to start|Working memory overflow|Pick the easiest 2-minute step")),
            Triple("sapped", "Sapped", Triple(-0.68f, 0.18f, "Energy pulled out through roots|Parasitic demands on time|Plug the energy leaks")),
            Triple("drowsy", "Drowsy", Triple(-0.25f, 0.10f, "Heavy blinks, comfortable warmth|Transitioning toward slumber|Embrace the natural sleep cycle")),
            Triple("slumped", "Slumped", Triple(-0.55f, 0.15f, "Collapsed torso in chair|Physical fatigue overtaking posture|Lie down properly")),
            Triple("spent_force", "Powerless Fatigue", Triple(-0.75f, 0.12f, "Zero push left in muscles|Acknowledge human limits|Surrender to the rest cycle")),
            Triple("recovering_fatigue", "Convalescing", Triple(0.20f, 0.20f, "Quiet bedrest, gentle sips|System repairing after exhaustion|Honor the sacred healing pace"))
        )
        for (item in fatigueData) {
            val parts = item.third.third.split("|")
            list.add(e(item.first, item.second, EmotionCategory.OVERWHELM_FATIGUE, item.third.first, item.third.second, parts[0], parts[1], parts[2]))
        }

        // 11. VULNERABILITY & SHAME (40 emotions)
        val shameData = listOf(
            Triple("ashamed", "Ashamed", Triple(-0.85f, 0.55f, "Hunched shoulders, downcast eyes, hot neck|Perception of fundamental flaw or wrongdoing|Differentiate guilt (behavior) from shame (self)")),
            Triple("guilty", "Guilty", Triple(-0.75f, 0.65f, "Aching chest, remorseful thoughts|Awareness of violating personal ethics|Make amends and take corrective action")),
            Triple("embarrassed", "Embarrassed", Triple(-0.60f, 0.70f, "Flushed red cheeks, nervous giggle, looking away|Exposure of clumsy or awkward act|Laugh at common human clumsiness")),
            Triple("humiliated", "Humiliated", Triple(-0.92f, 0.75f, "Burning ears, shrinking core, nausea|Public degradation of status or dignity|Reclaim your unassailable inner dignity")),
            Triple("mortified", "Mortified", Triple(-0.88f, 0.80f, "Wishing ground would swallow, face on fire|Extreme acute social embarrassment|Share the story with a friend to deflate it")),
            Triple("vulnerable", "Vulnerable", Triple(-0.35f, 0.50f, "Soft exposed throat, fluttering heart|Opening inner self without armor|Honor your courage to be real")),
            Triple("exposed", "Exposed", Triple(-0.65f, 0.65f, "Feeling naked under spotlight, cold chills|Loss of protective privacy|Put on grounding, comfortable clothes")),
            Triple("self_conscious", "Self-Conscious", Triple(-0.50f, 0.60f, "Awkward posture, hyperaware of body|Over-monitoring social perception|Shift focus outward onto others")),
            Triple("unworthy", "Unworthy", Triple(-0.80f, 0.30f, "Shrinking posture, quiet voice|False belief of being undeserving|Affirm: 'I am worthy simply because I exist'")),
            Triple("inadequate", "Inadequate", Triple(-0.70f, 0.45f, "Slumped chest, feeling small|Comparing self negatively to others|Focus on your personal baseline progress")),
            Triple("imposter", "Imposter Syndrome", Triple(-0.65f, 0.65f, "Tense neck, fearing discovery|Attributing success to luck instead of skill|Document your factual achievements")),
            Triple("flawed", "Defective", Triple(-0.82f, 0.35f, "Heavy gut, self-loathing thoughts|Core shame wound activated|Offer compassion to your inner child")),
            Triple("regretful_shame", "Contrite", Triple(-0.68f, 0.45f, "Bowed head, sincere apology|Desire to restore ethical harmony|Deliver a clean, non-defensive apology")),
            Triple("remorseful", "Remorseful", Triple(-0.78f, 0.55f, "Aching throat, heavy heart|Grief over harm caused to another|Make restitution where possible")),
            Triple("disgraced", "Disgraced", Triple(-0.90f, 0.60f, "Cast out posture, heavy gloom|Loss of community honor|Rebuild character step by step")),
            Triple("sheepish", "Sheepish", Triple(-0.45f, 0.40f, "Sideways glance, shy grin|Admitting small blunder|Own it with good humor")),
            Triple("chagrined", "Chagrined", Triple(-0.55f, 0.50f, "Grimace, rubbing back of neck|Annoyed embarrassment at mistake|Extract the lesson and move on")),
            Triple("abashed", "Abashed", Triple(-0.60f, 0.45f, "Lowered eyelashes, quiet posture|Feeling humbled by unexpected praise/flaw|Accept feedback with grace")),
            Triple("defensive_shame", "Prickly Defensiveness", Triple(-0.65f, 0.75f, "Spiky tone, crossed arms, counter-attack|Armor to conceal underlying shame|Lower the shield and examine the tender spot")),
            Triple("self_critical", "Self-Critical", Triple(-0.70f, 0.60f, "Internal harsh judge voice|Perfectionist demand for safety|Replace critic with inner supportive coach")),
            Triple("penitent", "Penitent", Triple(-0.70f, 0.35f, "Kneeling posture, quiet reflection|Repentance and turning of direction|Walk the new honorable path")),
            Triple("scorned_shame", "Discredited", Triple(-0.75f, 0.55f, "Shrinking in meeting, muted voice|Feeling dismissed as incompetent|Speak your verified truth calmly")),
            Triple("unclean_shame", "Tainted", Triple(-0.80f, 0.40f, "Urge to scrub skin, heavy gloom|Shame projected as dirty sensation|Remember your pure essence is intact")),
            Triple("diminished", "Diminished", Triple(-0.68f, 0.30f, "Feeling two inches tall|Loss of perceived stature|Stand tall and take up space")),
            Triple("self_effacing", "Self-Effacing", Triple(-0.40f, 0.35f, "Stepping out of photo, fading back|Habit of minimizing own presence|Claim your rightful seat at the table")),
            Triple("inferior", "Inferior", Triple(-0.75f, 0.40f, "Looking up with envy and defeat|Measuring against external ideals|Honor your unique gifts and path")),
            Triple("chastised", "Chastised", Triple(-0.65f, 0.50f, "Red ears, stinging reprimand|Pain of receiving public correction|Separate useful feedback from harsh delivery")),
            Triple("disowned_parts", "Self-Rejecting", Triple(-0.80f, 0.50f, "Disgust at own emotions/habits|Internal civil war against shadow|Embrace the rejected parts with curiosity")),
            Triple("timid_shame", "Timid", Triple(-0.45f, 0.40f, "Soft whisper, hesitant steps|Fear of making mistakes or noise|Speak up with gentle conviction")),
            Triple("cowed", "Cowed", Triple(-0.78f, 0.50f, "Submissive posture, averted gaze|Intimidated into self-silencing|Reconnect with your backbone")),
            Triple("belittled", "Belittled", Triple(-0.82f, 0.65f, "Stinging tear in eye, tight jaw|Being treated as small and trivial|Remove yourself from dismissive company")),
            Triple("self_blaming", "Self-Blaming", Triple(-0.72f, 0.60f, "Taking all fault on own shoulders|False belief of causing others' actions|Return responsibilities to their rightful owners")),
            Triple("awkward_shame", "Socially Awkward", Triple(-0.48f, 0.55f, "Uncertain hand gestures, dry mouth|Self-consciousness in group setting|Find one friendly face to converse with")),
            Triple("bashful", "Bashful", Triple(0.20f, 0.50f, "Blushing smile, ducking head|Sweet modesty under attention|Enjoy the warm attention")),
            Triple("discomfited", "Discomfited", Triple(-0.52f, 0.55f, "Shifting in seat, nervous collar pull|Uneasy under probing question|Politely declare your private boundary")),
            Triple("crestfallen_shame", "Disillusioned with Self", Triple(-0.72f, 0.35f, "Heavy sigh looking in mirror|Failing to live up to personal ideal|Give yourself the gift of being human")),
            Triple("unmasked", "Unmasked", Triple(-0.65f, 0.70f, "Sudden panic of losing facade|Fear of people seeing behind curtain|Real intimacy begins where facade ends")),
            Triple("ostracized", "Ostracized", Triple(-0.88f, 0.60f, "Cold silence from group, excluded|Pain of tribal rejection|Find your true tribe who values you")),
            Triple("shamed_silence", "Silenced", Triple(-0.75f, 0.30f, "Lump in throat, swallow back voice|Conditioned belief that voice is bad|Write down what was forbidden to say")),
            Triple("redeemed", "Redeemed", Triple(0.85f, 0.60f, "Full breath, warm spine, tears of release|Liberation from shame into love|Walk forward in full freedom"))
        )
        for (item in shameData) {
            val parts = item.third.third.split("|")
            list.add(e(item.first, item.second, EmotionCategory.VULNERABILITY_SHAME, item.third.first, item.third.second, parts[0], parts[1], parts[2]))
        }

        // 12. CONFUSION & AMBIVALENCE (40 emotions)
        val confusionData = listOf(
            Triple("confused", "Confused", Triple(-0.35f, 0.55f, "Furrowed brow, tilted head, hesitation|Dissonance between data and models|Break the problem down into basics")),
            Triple("perplexed", "Perplexed", Triple(-0.40f, 0.60f, "Scratching chin, staring at problem|Complexity exceeding simple explanation|Map out the knowns and unknowns")),
            Triple("baffled", "Baffled", Triple(-0.45f, 0.65f, "Shrugging shoulders, wide blank eyes|Encountering total enigma|Consult an outside expert perspective")),
            Triple("torn", "Torn", Triple(-0.50f, 0.70f, "Pulling in chest between two directions|Conflict between opposing values|Identify which core value takes priority")),
            Triple("ambivalent", "Ambivalent", Triple(0.00f, 0.50f, "Simultaneous like and dislike|Coexistence of opposing emotions|Acknowledge that both feelings are valid")),
            Triple("indecisive", "Indecisive", Triple(-0.40f, 0.60f, "Hesitating hand, analytical paralysis|Fear of choosing wrong option|Flip a coin to reveal your true preference")),
            Triple("disoriented", "Disoriented", Triple(-0.60f, 0.70f, "Dizziness, loss of spatial/mental bearings|Sudden shift in context or reality|Sit down and identify 5 physical objects around you")),
            Triple("uncertain", "Uncertain", Triple(-0.30f, 0.45f, "Slow steps, questioning tone|Lack of predictable future data|Focus on today's next faithful step")),
            Triple("hesitant", "Hesitant", Triple(-0.25f, 0.40f, "Foot hovering above ground|Caution before irreversible move|Weigh the downside risk")),
            Triple("conflicted", "Conflicted", Triple(-0.55f, 0.65f, "Knot in stomach, tense jaw|Internal war between duty and desire|Give both sides a voice in writing")),
            Triple("bewildered", "Bewildered", Triple(-0.50f, 0.65f, "Lost expression, looking around|Overloaded by chaotic developments|Pause and wait for the dust to settle")),
            Triple("ambiguous", "Ambiguous", Triple(-0.20f, 0.35f, "Shifting interpretation|Unclear boundaries and cues|Ask clarifying questions")),
            Triple("puzzled", "Puzzled", Triple(-0.25f, 0.45f, "Pursed lips, squinting eyes|Trying to fit mismatched pieces|Look for the missing piece")),
            Triple("mystified_conf", "Mystified", Triple(-0.35f, 0.50f, "Shaking head in wonder and doubt|Inability to trace causal links|Test small empirical hypotheses")),
            Triple("disquieted_conf", "Unsettled Doubt", Triple(-0.45f, 0.50f, "Restless gut, second-guessing|Loss of foundational confidence|Distinguish real risk from imagination")),
            Triple("foggy", "Brain Fog", Triple(-0.40f, 0.20f, "Pressure behind forehead, slow recall|Cognitive fatigue or inflammation|Rest, drink water with electrolytes")),
            Triple("dazed", "Dazed", Triple(-0.45f, 0.30f, "Slow blinking, muted reactions|Shock from impact or news|Sit down in quiet space")),
            Triple("muddled", "Muddled", Triple(-0.40f, 0.40f, "Jumbled thoughts, mixing words|Congested working memory|Write thoughts on separate sticky notes")),
            Triple("equivocal", "Equivocal", Triple(0.00f, 0.30f, "Balanced scales, non-committal|Seeing merit on all sides|Define a decision deadline")),
            Triple("flummoxed", "Flummoxed", Triple(-0.48f, 0.65f, "Hands in air, comical bewilderment|Utterly stumped by absurd hurdle|Laugh at the absurdity and reboot")),
            Triple("bothered_doubt", "Second-Guessing", Triple(-0.50f, 0.55f, "Reviewing sent email repeatedly|Anxiety over past choices|Trust your past self made the best choice with data had")),
            Triple("wavering", "Wavering", Triple(-0.35f, 0.50f, "Swaying posture, unstable breath|Fluctuating between options|Anchor in your long-term goal")),
            Triple("lost", "Lost", Triple(-0.70f, 0.45f, "Looking at blank map, hollow stomach|Absence of clear path forward|Stop moving; ask for directions")),
            Triple("skeptical_conf", "Skeptical", Triple(-0.20f, 0.45f, "Arched eyebrow, leaning back|Healthy doubt protecting against fraud|Verify facts with multiple independent sources")),
            Triple("nonplussed", "Nonplussed", Triple(-0.30f, 0.40f, "Frozen in reaction, neutral face|So surprised unsure how to react|Take a moment before responding")),
            Triple("disillusioned", "Disillusioned", Triple(-0.65f, 0.35f, "Sigh of disappointment, veil lifted|Loss of romanticized belief|Welcome the raw truth as real foundation")),
            Triple("adrift", "Adrift", Triple(-0.60f, 0.25f, "Floating aimlessly, lacking anchor|Loss of central mission or anchor|Set a small meaningful daily anchor")),
            Triple("unsure", "Unsure", Triple(-0.25f, 0.35f, "Quiet tone, tentative gesture|Lack of conviction|Gather more data or test small prototype")),
            Triple("mixed_feelings", "Mixed Feelings", Triple(0.05f, 0.45f, "Heart warm, gut nervous|Simultaneous joy and sorrow|Hold space for complex maturity")),
            Triple("overthinking", "Overthinking", Triple(-0.55f, 0.75f, "Headache, spinning mental carousel|Analysis paralysis from fear|Shift from thinking to physical doing")),
            Triple("bewitched_conf", "Enigmatic", Triple(0.10f, 0.50f, "Intrigued yet baffled gaze|Fascinated by paradox|Contemplate the paradox without forcing solution")),
            Triple("divided", "Divided Heart", Triple(-0.45f, 0.55f, "Tension between mind and emotion|Mind says yes, gut says no|Listen to somatic wisdom of the body")),
            Triple("aimless", "Aimless", Triple(-0.40f, 0.20f, "Unfocused wandering|Temporary lack of target|Enjoy wandering as open exploration")),
            Triple("inconclusive", "Inconclusive", Triple(-0.15f, 0.30f, "Neutral shrug, open palms|Insufficient evidence|Gather additional trial results")),
            Triple("paralyzed_choice", "Choice Overload", Triple(-0.50f, 0.65f, "Overwhelmed by menu of 50 items|Too many variables|Eliminate 80% of options immediately")),
            Triple("unanchored", "Unanchored", Triple(-0.55f, 0.30f, "Feeling unmoored on ocean|Missing foundational boundaries|Return to your daily cornerstone routines")),
            Triple("curious_doubt", "Investigative Doubt", Triple(0.20f, 0.55f, "Spark in eye questioning assumption|Scientific pursuit of truth|Test the assumption empirically")),
            Triple("surreal", "Surreal", Triple(0.00f, 0.60f, "Dreamlike perception, pinch cheek|Experience feeling like fiction|Anchor in tactile sensory contact")),
            Triple("chameleon", "Fragmented", Triple(-0.50f, 0.45f, "Wearing different masks in rush|Loss of cohesive identity|Reclaim your singular authentic self")),
            Triple("seeking_clarity", "Seeking Clarity", Triple(0.30f, 0.50f, "Clearing desk, taking deep breaths|Active journey toward understanding|Write down your core question clearly"))
        )
        for (item in confusionData) {
            val parts = item.third.third.split("|")
            list.add(e(item.first, item.second, EmotionCategory.CONFUSION_AMBIVALENCE, item.third.first, item.third.second, parts[0], parts[1], parts[2]))
        }

        // 13. GROUNDING & STRENGTH (40 emotions)
        val groundData = listOf(
            Triple("grounded_st", "Grounded", Triple(0.75f, 0.30f, "Weight in feet, connected to earth|Physical and emotional solidity|Feel the support of gravity")),
            Triple("resilient", "Resilient", Triple(0.80f, 0.55f, "Flexible spine, rebound posture|Capacity to absorb shock and recover|Honor your strength to bounce back")),
            Triple("courageous", "Courageous", Triple(0.85f, 0.75f, "Active heartbeat, forward motion despite fear|Acting on what is right despite tremors|Let core values steer your action")),
            Triple("sovereign", "Sovereign", Triple(0.85f, 0.60f, "Dignified posture, calm gaze|Autonomous master of inner domain|Exercise agency with calm authority")),
            Triple("clearheaded", "Clear-Headed", Triple(0.75f, 0.40f, "Bright eyes, cool forehead|Free from confusion or distortion|Prioritize immediate next 3 actions")),
            Triple("accepting", "Accepting", Triple(0.65f, 0.20f, "Open palms, soft belly|Radical consent to reality as it is|Repeat: 'It is what it is right now'")),
            Triple("steadfast", "Steadfast", Triple(0.80f, 0.50f, "Rooted stance, unwavering gaze|Loyalty to principles under storm|Stand firm on your foundational truth")),
            Triple("anchored", "Anchored", Triple(0.78f, 0.25f, "Heavy dropped weight in core|Stability preventing drift|Remember your ultimate purpose")),
            Triple("balanced_st", "Balanced", Triple(0.76f, 0.35f, "Equal weight across legs, centered spine|Equilibrium in all dimensions|Walk the middle path")),
            Triple("unshakeable", "Unshakeable", Triple(0.88f, 0.45f, "Mountain-like solidity, slow pulse|Immunity to transient panic|Be the mountain in the storm")),
            Triple("stoic", "Stoic", Triple(0.68f, 0.30f, "Controlled breath, firm lips|Mastery over emotional turbulence|Focus only on what you can control")),
            Triple("integrative", "Integrated", Triple(0.85f, 0.40f, "Cohesion of thought, feeling, action|Wholeness without internal civil war|Act from your unified whole")),
            Triple("authentic", "Authentic", Triple(0.82f, 0.45f, "Relaxed face, honest resonant voice|Living in congruence with truth|Speak and act without pretense")),
            Triple("sturdy", "Sturdy", Triple(0.74f, 0.40f, "Broad solid frame, grounded footing|Physical and moral fortitude|Offer your shoulder to lean on")),
            Triple("undeterred", "Undeterred", Triple(0.78f, 0.60f, "Unwavering forward step|Overcoming roadblock without despair|Find the detour and keep walking")),
            Triple("fortified", "Fortified", Triple(0.80f, 0.55f, "Recharged core, shielded posture|Inner strength renewed after trial|Step forward with reinforced armor")),
            Triple("wise", "Wise", Triple(0.86f, 0.30f, "Soft compassionate gaze, slow words|Integration of deep life experience|Offer measured discernment")),
            Triple("noble", "Noble", Triple(0.82f, 0.50f, "Erect spine, generous spirit|Upholding the highest human ideals|Choose the high road")),
            Triple("tenacious_st", "Tenacious", Triple(0.76f, 0.70f, "Strong grip, determined jaw|Refusal to let go of meaningful good|Hold on until the breakthrough")),
            Triple("composed_st", "Self-Possessed", Triple(0.80f, 0.35f, "Calm breathing, measured actions|Full command of one's faculties|Lead with quiet confidence")),
            Triple("prudent", "Prudent", Triple(0.70f, 0.35f, "Thoughtful gaze, measured pace|Wise foresight in decision making|Save resources for the right time")),
            Triple("dignified", "Dignified", Triple(0.78f, 0.40f, "Graceful posture, calm eye contact|Self-respect that commands respect|Maintain your poise")),
            Triple("resolute_st", "Iron-Willed", Triple(0.82f, 0.70f, "Locked core, firm stride|Unbending resolve in face of temptation|Stay true to your vow")),
            Triple("resourceful", "Resourceful", Triple(0.78f, 0.65f, "Quick scanning eyes, creative hands|Ability to solve puzzles with available tools|Look at what you have around you")),
            Triple("grounded_power", "Quiet Power", Triple(0.86f, 0.50f, "Deep diaphragmatic voice, stillness|Power that does not need to shout|Let your presence do the talking")),
            Triple("resilient_peace", "Indomitable", Triple(0.88f, 0.60f, "Unbroken spirit after hardship|Inherent resilience of human heart|Rebuild from ashes with pride")),
            Triple("equanimous_st", "Equanimous Strength", Triple(0.84f, 0.30f, "Level gaze in praise or blame|Freedom from tyranny of external opinions|Stay steady in your own truth")),
            Triple("honorable", "Honorable", Triple(0.80f, 0.45f, "Open honest hand, straight posture|Fidelity to word and bond|Keep your promise")),
            Triple("unflinching", "Unflinching", Triple(0.80f, 0.60f, "Direct gaze, steady pulse|Willingness to look at hard truth|Confront the reality without blinking")),
            Triple("patient_strength", "Patient", Triple(0.75f, 0.25f, "Unrushed breath, relaxed shoulders|Trust in the unfolding of seasons|Allow the seed to grow in its time")),
            Triple("invulnerable_center", "Invulnerable Center", Triple(0.85f, 0.35f, "Golden sphere sensation in heart|Core of self that cannot be harmed|Rest in your inviolable spirit")),
            Triple("self_reliant", "Self-Reliant", Triple(0.78f, 0.50f, "Capable hands, clear planning|Ability to care for own needs|Take charge of your destiny")),
            Triple("persevering", "Persevering", Triple(0.76f, 0.60f, "Rhythmic pacing, steady breath|Continuing through long desert|Keep taking one step after another")),
            Triple("centered_presence", "Present", Triple(0.80f, 0.30f, "Full embodiment in here and now|Arriving fully in this moment|Feel the breath entering your lungs")),
            Triple("rooted", "Deeply Rooted", Triple(0.82f, 0.25f, "Roots extending into earth|Nourishment from deep foundations|Drink from deep ancestral wells")),
            Triple("empowered_ground", "Empowered Ground", Triple(0.84f, 0.55f, "Strong spine, open chest|Authority to steer your life|Claim your sovereignty")),
            Triple("tempered", "Tempered", Triple(0.80f, 0.45f, "Hardened steel resilience|Strengthened through fire of trials|You are stronger for having survived")),
            Triple("solid", "Rock-Solid", Triple(0.78f, 0.35f, "Firm stance, dependable presence|Unshaken support for others|Stand as a pillar")),
            Triple("serene_strength", "Serene Strength", Triple(0.88f, 0.30f, "Gentle smile with lion heart|Harmonious union of peace and power|Lead with compassion and courage")),
            Triple("transcendent_resilience", "Transcendent Wholeness", Triple(0.95f, 0.45f, "Vast grounded heart, boundless peace|Complete realization of indomitable self|Live from this place of wholeness"))
        )
        for (item in groundData) {
            val parts = item.third.third.split("|")
            list.add(e(item.first, item.second, EmotionCategory.GROUNDING_RESILIENCE, item.third.first, item.third.second, parts[0], parts[1], parts[2]))
        }

        // 14. GUILT & REGRET (35 emotions)
        val guiltData = listOf(
            Triple("guilt_core", "Guilty", Triple(-0.70f, 0.65f, "Heavy sinking in chest, downward glance|Conscience signaling violation of ethics|Identify what amends can be made")),
            Triple("guilt_remorse", "Remorseful", Triple(-0.80f, 0.60f, "Aching throat, deep moral pain|Deep sorrow over harm caused to another|Offer a sincere and humble apology")),
            Triple("guilt_regretful", "Regretful", Triple(-0.65f, 0.50f, "Sigh of yearning, wishing to undo|Wish to choose differently in retrospect|Extract the hard wisdom for the future")),
            Triple("guilt_penitent", "Penitent", Triple(-0.72f, 0.45f, "Bowed head, contrite heart|Desire to atone and make restitution|Commit to corrective action")),
            Triple("guilt_contrite", "Contrite", Triple(-0.75f, 0.40f, "Broken ego, softened spirit|Sincere humility in wrongdoing|Listen without defensiveness")),
            Triple("guilt_self_blame", "Self-Blaming", Triple(-0.78f, 0.70f, "Pointing inward, mental condemnation|Taking excessive fault on self|Distinguish your part from external factors")),
            Triple("guilt_culpable", "Culpable", Triple(-0.60f, 0.55f, "Tension in jaw, acknowledging mistake|Acceptance of accountability|Own the outcome clearly")),
            Triple("guilt_apologetic", "Apologetic", Triple(-0.55f, 0.50f, "Open hesitant hands, soft tone|Desire to repair relationship breach|Speak clear words of ownership")),
            Triple("guilt_chagrined", "Chagrined", Triple(-0.50f, 0.45f, "Blushing face, lowered eyes|Embarrassment coupled with regret|Accept that mistakes are human")),
            Triple("guilt_rueful", "Rueful", Triple(-0.48f, 0.40f, "Wry smile with sad eyes|Affectionate awareness of folly|Forgive your earlier ignorance")),
            Triple("guilt_self_accusing", "Self-Accusing", Triple(-0.82f, 0.75f, "Throbbing temples, sharp internal critic|Internal prosecutor on trial|Replace cruelty with firm self-correction")),
            Triple("guilt_burdened", "Burdened by Guilt", Triple(-0.85f, 0.55f, "Heavy shoulders weighed down|Unresolved debt of conscience|Write out what restitution looks like")),
            Triple("guilt_repentant", "Repentant", Triple(-0.68f, 0.50f, "Tearful resolve, turning around|Turning away from harmful patterns|Walk the new path consistently")),
            Triple("guilt_sheepish", "Sheepish", Triple(-0.40f, 0.45f, "Awkward grin, scratching head|Caught in small blunder|Acknowledge the awkwardness gracefully")),
            Triple("guilt_conscience_pangs", "Pangs of Conscience", Triple(-0.74f, 0.68f, "Sudden sharp pinch in heart|Intuitive alert of ethical breach|Stop the action causing moral dissonance")),
            Triple("guilt_fault_ridden", "Fault-Ridden", Triple(-0.76f, 0.60f, "Cringing posture, hyper-focus on errors|Feeling everything went wrong because of you|Separate facts from emotional projection")),
            Triple("guilt_self_condemning", "Self-Condemning", Triple(-0.88f, 0.70f, "Cold gut, crushing self-talk|Judging self unworthy of forgiveness|Offer yourself restorative grace")),
            Triple("guilt_morally_pained", "Morally Pained", Triple(-0.78f, 0.55f, "Somatic knot in diaphragm|Integrity violated by circumstance|Take the honorable moral step")),
            Triple("guilt_wracked", "Wracked with Guilt", Triple(-0.90f, 0.80f, "Physical nausea, sleepless nights|Severe moral crisis over consequence|Seek professional or spiritual counsel")),
            Triple("guilt_chastened", "Chastened", Triple(-0.62f, 0.35f, "Subdued quietness, sobered mind|Humbled by hard lesson|Integrate the humility as strength")),
            Triple("guilt_conscience_stricken", "Conscience-Stricken", Triple(-0.80f, 0.65f, "Restless chest, inability to relax|Demanding amends be made|Do the right thing immediately")),
            Triple("guilt_undoing_wish", "Wishing to Undo", Triple(-0.70f, 0.60f, "Staring at hands in disbelief|Desire for time reversal|Accept that what is done cannot be undone")),
            Triple("guilt_mortified_action", "Mortified by Actions", Triple(-0.82f, 0.75f, "Face burning, stomach churning|Extreme horror at one's own blunder|Take a grounding breath; this too shall pass")),
            Triple("guilt_scrupulous", "Scrupulous", Triple(-0.60f, 0.70f, "Obsessive checking of intent|Hyper-vigilance over moral perfection|Practice realistic human tolerance")),
            Triple("guilt_self_punishing", "Self-Punishing", Triple(-0.85f, 0.75f, "Deprivation of joy, harsh inner voice|Believing suffering earns redemption|True redemption is living rightly, not self-harm")),
            Triple("guilt_indebted", "Indebted", Triple(-0.55f, 0.45f, "Obligated posture, heavy duty|Sense of unpaid emotional balance|Clarify healthy boundaries of duty")),
            Triple("guilt_belated_sorrow", "Belated Sorrow", Triple(-0.72f, 0.40f, "Mournful reflection years later|Realizing consequences delayed in time|Send blessing to those you affected")),
            Triple("guilt_atoning", "Atoning", Triple(-0.50f, 0.55f, "Focused diligent labor|Active restoration of harmony|Channel effort into constructive good")),
            Triple("guilt_complicit", "Complicit", Triple(-0.75f, 0.60f, "Uneasy silence, downcast face|Guilt of standing by during injustice|Speak up for the vulnerable")),
            Triple("guilt_betrayal_remorse", "Remorseful Betrayer", Triple(-0.88f, 0.70f, "Sharp agony in chest, burning eyes|Grief over breaking trust of beloved|Accept consequences with full dignity")),
            Triple("guilt_hypocrisy_pain", "Pain of Hypocrisy", Triple(-0.74f, 0.65f, "Dry mouth, internal dissonance|Acting against preached ideals|Align your private life with your speech")),
            Triple("guilt_neglectful_remorse", "Neglectful Regret", Triple(-0.70f, 0.50f, "Aching regret over missed visits|Grief of taking loved ones for granted|Reach out to those still here")),
            Triple("guilt_sober_amends", "Seeking Amends", Triple(-0.45f, 0.50f, "Calm deliberate speech, steady eye|Mature commitment to reconciliation|Make restitution without making it about your ego")),
            Triple("guilt_forgiven", "Longing for Forgiveness", Triple(-0.65f, 0.55f, "Open supplicant hands|Yearning for relational restoration|Start by extending forgiveness to self")),
            Triple("guilt_reconciled", "Humbled Wisdom", Triple(0.20f, 0.35f, "Release of tension, deep exhale|Integration of painful moral lesson|Walk forward in conscious integrity"))
        )
        for (item in guiltData) {
            val parts = item.third.third.split("|")
            list.add(e(item.first, item.second, EmotionCategory.GUILT_REGRET, item.third.first, item.third.second, parts[0], parts[1], parts[2]))
        }

        // 15. JEALOUSY & ENVY (35 emotions)
        val envyData = listOf(
            Triple("envy_core", "Envious", Triple(-0.65f, 0.60f, "Green heat in gut, critical glare|Discontent sparked by another's gain|Identify what you truly desire for yourself")),
            Triple("envy_jealous", "Jealous", Triple(-0.72f, 0.75f, "Possessive tension, watchful eyes|Fear of losing valued bond to rival|Communicate vulnerable feelings of insecurity")),
            Triple("envy_covetous", "Covetous", Triple(-0.58f, 0.65f, "Hungry stare, grasping hands|Craving what belongs to another|Cultivate gratitude for what is in your hands")),
            Triple("envy_resentful_comparison", "Resentful Comparison", Triple(-0.70f, 0.60f, "Sour taste, dismissive remark|Bitterness at another's perceived ease|Remember everyone fights hidden battles")),
            Triple("envy_green_eyed", "Green-Eyed", Triple(-0.68f, 0.70f, "Tight stomach, stinging eyes|Toxic perception of another's fortune|Breathe out the poison of comparison")),
            Triple("envy_threatened_insecurity", "Threatened Insecurity", Triple(-0.75f, 0.80f, "Racing heart, defensive armoring|Perception of being replaced or eclipsed|Ground in your irreplaceable unique essence")),
            Triple("envy_bitter_rivalry", "Bitter Rivalry", Triple(-0.74f, 0.85f, "Locked jaw, measuring every score|Hyper-competitive hostility|Shift from beating them to mastering self")),
            Triple("envy_possessive", "Possessive", Triple(-0.65f, 0.70f, "Clinging grip, vigilant scrutiny|Fear of loss disguised as control|Loosen your grasp; love requires freedom")),
            Triple("envy_begrudging", "Begrudging", Triple(-0.50f, 0.45f, "Forced applause, tight lips|Reluctant acknowledgment of another|Practice genuine generosity of spirit")),
            Triple("envy_fomo", "Fear of Missing Out", Triple(-0.55f, 0.75f, "Scrolling frenzy, frantic heartbeat|Anxiety that others live better lives|Disconnect from digital feeds for 24h")),
            Triple("envy_inadequate_compare", "Comparison Inadequacy", Triple(-0.76f, 0.50f, "Hollow chest, diminished spine|Feeling small next to someone's highlight|Compare only to who you were yesterday")),
            Triple("envy_territorial", "Territorial", Triple(-0.60f, 0.75f, "Standing tall, marking boundaries|Defense of status or social turf|Cultivate abundance mentality")),
            Triple("envy_suspicious_jealousy", "Suspiciously Jealous", Triple(-0.78f, 0.80f, "Snoop impulse, racing pulse|Projected betrayal and hyper-vigilance|Have an honest transparent conversation")),
            Triple("envy_competitive_malice", "Competitive Malice", Triple(-0.80f, 0.75f, "Schadenfreude urge, cold smirk|Desire for rival's downfall|Cleanse mind with self-respect")),
            Triple("envy_spiteful_envy", "Spiteful Envy", Triple(-0.75f, 0.70f, "Venom in throat, sharp sarcasm|Tasting bitterness in another's triumph|Wish them well to free your own soul")),
            Triple("envy_left_out", "Feeling Left Out", Triple(-0.68f, 0.45f, "Lump in throat, standing on perimeter|Excluded from inner circle|Create your own welcoming circle")),
            Triple("envy_suppressed_resent", "Suppressed Resentment", Triple(-0.70f, 0.55f, "Clenched teeth behind polite smile|Unexpressed grudge at unfair rewards|Channel grievance into assertive merit")),
            Triple("envy_discontent_compare", "Discontented Comparison", Triple(-0.58f, 0.50f, "Heavy sigh looking at another's life|Depreciation of one's current path|Celebrate your unique timeline")),
            Triple("envy_coveting_success", "Coveting Success", Triple(-0.62f, 0.65f, "Restless pacing, hunger for prestige|Longing for external validation|Define your internal standard of success")),
            Triple("envy_grudging_admiration", "Grudging Admiration", Triple(-0.35f, 0.50f, "Nod with crossed arms|Recognizing excellence with slight envy|Convert envy into study and mentorship")),
            Triple("envy_burning_envy", "Burning Envy", Triple(-0.82f, 0.85f, "Hot chest, spinning thoughts|Fierce internal fire of jealousy|Cool down with nature walk and cold water")),
            Triple("envy_acutely_possessive", "Acutely Possessive", Triple(-0.80f, 0.80f, "Tight clutching, desperate defense|Obsessive guarding of relationship|Heal the underlying attachment wound")),
            Triple("envy_threatened_rival", "Threatened by Rival", Triple(-0.72f, 0.78f, "Adrenaline spike, sizing up newcomer|Perception of lost supremacy|Collaborate instead of competing")),
            Triple("envy_smoldering_insecurity", "Smoldering Insecurity", Triple(-0.66f, 0.55f, "Simmering unease beneath surface|Constant fear of being second best|Affirm your innate sovereignty")),
            Triple("envy_jealous_obsession", "Jealous Obsession", Triple(-0.85f, 0.88f, "Mental carousel around rival|Loss of focus on own life|Redirect 100% of mental energy back to you")),
            Triple("envy_resenting_privilege", "Resenting Privilege", Triple(-0.64f, 0.60f, "Anger at unearned advantages|Moral frustration at inequality|Work strategically to level the field")),
            Triple("envy_covetous_pining", "Covetous Pining", Triple(-0.60f, 0.50f, "Aching stomach, empty palms|Yearning for what others possess|Focus on creating rather than consuming")),
            Triple("envy_diminished_others", "Diminished by Others", Triple(-0.70f, 0.40f, "Slumped shoulders, quiet retreat|Feeling overshadowed in public|Stand tall in your own light")),
            Triple("envy_begrudging_praise", "Begrudging Praise", Triple(-0.45f, 0.40f, "Tight smile, short applause|Difficulty celebrating others' wins|Rejoice in others' joy (Mudita)")),
            Triple("envy_eclipsed", "Feeling Eclipsed", Triple(-0.68f, 0.45f, "Cold shadow over posture|Feeling overlooked for another|Your time in the sun will come")),
            Triple("envy_bitterly_compete", "Bitterly Competitive", Triple(-0.74f, 0.80f, "Intense stare, aggressive drive|Need to destroy the competition|Remember peace is greater than supremacy")),
            Triple("envy_invidious", "Invidious", Triple(-0.65f, 0.55f, "Looking for flaws in successful people|Defensive rationalization of envy|Honor excellence wherever it appears")),
            Triple("envy_coveting_recognition", "Hungry for Recognition", Triple(-0.55f, 0.65f, "Tense neck, searching for applause|Craving external praise|Validate your own hard work first")),
            Triple("envy_jealous_angst", "Jealous Angst", Triple(-0.75f, 0.70f, "Knot in solar plexus, churn|Agony of romantic uncertainty|Seek security in self-trust")),
            Triple("envy_transmuted", "Transmuted Aspiration", Triple(0.25f, 0.60f, "Sparks in eyes, upright spine|Turning envy into constructive inspiration|Let another's success prove what is possible"))
        )
        for (item in envyData) {
            val parts = item.third.third.split("|")
            list.add(e(item.first, item.second, EmotionCategory.JEALOUSY_ENVY, item.third.first, item.third.second, parts[0], parts[1], parts[2]))
        }

        // 16. LONELINESS & ISOLATION (35 emotions)
        val lonelyData = listOf(
            Triple("lonely_core", "Lonely", Triple(-0.72f, 0.35f, "Cold empty chest, aching solitude|Drive to seek social bonding and tribe|Reach out for one authentic conversation")),
            Triple("lonely_isolated", "Isolated", Triple(-0.78f, 0.25f, "Behind glass sensation, muted sounds|Separation from human community|Step into a shared public space or park")),
            Triple("lonely_alienated", "Alienated", Triple(-0.80f, 0.40f, "Feeling foreign among people|Profound disconnection from culture/peers|Find sub-cultures sharing your values")),
            Triple("lonely_estranged", "Estranged", Triple(-0.82f, 0.35f, "Severed chord sensation in spine|Painful rift with family or friend|Hold compassion while respecting boundaries")),
            Triple("lonely_forsaken", "Forsaken", Triple(-0.90f, 0.30f, "Hollow abdomen, desolate gaze|Belief of total desertion by all|You are never truly alone in human history")),
            Triple("lonely_abandoned", "Abandoned", Triple(-0.92f, 0.55f, "Primal chill, trembling chest|Fear of being discarded and left|Be the parent to your inner child")),
            Triple("lonely_excluded", "Excluded", Triple(-0.75f, 0.50f, "Cold periphery, watching doorway|Pain of intentional rejection|Seek spaces where you are celebrated")),
            Triple("lonely_disconnected", "Socially Disconnected", Triple(-0.65f, 0.30f, "Low engagement, scrolling alone|Absence of meaningful relational depth|Call a friend instead of texting")),
            Triple("lonely_misfit", "Misfit", Triple(-0.55f, 0.40f, "Square peg posture, awkward stance|Feeling unsuited for current crowd|Embrace your rare and distinctive mind")),
            Triple("lonely_ostracized", "Ostracized", Triple(-0.88f, 0.60f, "Silent cold shoulder, exile posture|Social exile by tribe or group|Build a new tribe aligned with truth")),
            Triple("lonely_solitary_ache", "Solitary Ache", Triple(-0.70f, 0.30f, "Ache in sternum during quiet evening|Desire for quiet presence of another|Listen to comforting warm music")),
            Triple("lonely_homesick_belong", "Homesick for Belonging", Triple(-0.74f, 0.35f, "Wistful gaze, hand on chest|Yearning for a spiritual or human home|Create a sanctuary in your current room")),
            Triple("lonely_invisible", "Feeling Invisible", Triple(-0.76f, 0.20f, "Transparent sensation, soft voice|Feeling unnoticed by people in room|Speak up clearly and take up space")),
            Triple("lonely_unheard", "Unheard", Triple(-0.68f, 0.45f, "Throat constriction, silenced words|Efforts to communicate falling flat|Write your thoughts where they are preserved")),
            Triple("lonely_unseen", "Unseen", Triple(-0.72f, 0.25f, "Veiled eyes, hidden personality|Lack of mirroring and understanding|Show your true art to trusted allies")),
            Triple("lonely_outcast", "Outcast", Triple(-0.82f, 0.45f, "Wandering steps, defensive jacket|Exclusion from dominant collective|Honor the tradition of noble outsiders")),
            Triple("lonely_unwanted", "Unwanted", Triple(-0.85f, 0.35f, "Shrinking posture, averted eyes|Belief of being a burden|Know that you have intrinsic right to exist")),
            Triple("lonely_yearning_connect", "Yearning for Connection", Triple(-0.65f, 0.50f, "Reaching posture, warm eyes|Hunger for mutual intimacy|Attend an interest club or workshop")),
            Triple("lonely_cold_solitude", "Cold Solitude", Triple(-0.70f, 0.20f, "Shiver in empty apartment|Solitude lacking warmth or purpose|Make hot tea and wrap in cozy wool")),
            Triple("lonely_forgotten", "Forgotten", Triple(-0.78f, 0.20f, "Unchecked phone, quiet mailbox|Sense of being deleted from memory|Initiate contact; do not wait to be found")),
            Triple("lonely_deserted", "Deserted", Triple(-0.86f, 0.30f, "Empty crossroads, wind in ears|Sudden departure of comrades|Keep walking forward on your quest")),
            Triple("lonely_companionless", "Companionless", Triple(-0.68f, 0.25f, "Solo dining, quiet walk|Absence of partner or teammate|Appreciate the freedom in solo journey")),
            Triple("lonely_hermetic_sadness", "Hermetic Sadness", Triple(-0.60f, 0.20f, "Curtained room, quiet sanctuary|Over-isolation turning melancholic|Open the windows and let sunlight in")),
            Triple("lonely_displaced", "Displaced", Triple(-0.74f, 0.40f, "Unfamiliar streets, unsettled steps|Loss of cultural or geographic roots|Connect with fellow expats or newcomers")),
            Triple("lonely_rootless", "Rootless", Triple(-0.70f, 0.30f, "Floating feet, unanchored mind|Lack of deep generational ties|Plant roots in today's daily soil")),
            Triple("lonely_cut_off", "Cut Off", Triple(-0.78f, 0.35f, "Wall erected, severed telephone wire|Inability to reach across divide|Keep your internal door unlocked")),
            Triple("lonely_emotional_exile", "Emotional Exile", Triple(-0.84f, 0.30f, "Living in shadow of community|Banishment for authenticity|Wear your authenticity with dignity")),
            Triple("lonely_untethered", "Untethered", Triple(-0.72f, 0.25f, "Drifting balloon sensation|Absence of relational anchors|Anchor in a daily rhythm and routine")),
            Triple("lonely_longing_tribe", "Longing for Tribe", Triple(-0.65f, 0.45f, "Scanning crowds for kindred souls|Seeking people of shared spirit|Share your niche passion online/in-person")),
            Triple("lonely_secluded_sorrow", "Secluded Sorrow", Triple(-0.76f, 0.20f, "Hiding grief behind closed door|Suffering without witnesses|Let one person witness your struggle")),
            Triple("lonely_void_intimacy", "Void of Intimacy", Triple(-0.80f, 0.35f, "Aching arms, hunger for touch|Lack of physical/emotional warmth|Hug a pet or practice self-soothing touch")),
            Triple("lonely_cast_aside", "Cast Aside", Triple(-0.85f, 0.40f, "Dropped on floor feeling, grief|Feeling discarded after use|Reclaim your unassailable self-value")),
            Triple("lonely_detached_world", "Detached from World", Triple(-0.75f, 0.20f, "Looking from orbit at earth|Depersonalized distance from crowds|Touch tree bark or cool water")),
            Triple("lonely_unaccompanied", "Unaccompanied", Triple(-0.55f, 0.25f, "Walking solo through life's trials|Braving hardship without co-pilot|Take pride in your solo courage")),
            Triple("lonely_rich_solitude", "Sacred Solitude", Triple(0.35f, 0.20f, "Warm spacious silence in room|Transmuting isolation into communion|Enjoy deep friendship with your own soul"))
        )
        for (item in lonelyData) {
            val parts = item.third.third.split("|")
            list.add(e(item.first, item.second, EmotionCategory.LONELINESS_ISOLATION, item.third.first, item.third.second, parts[0], parts[1], parts[2]))
        }

        // 17. NUMBNESS & APATHY (35 emotions)
        val numbData = listOf(
            Triple("numb_core", "Numb", Triple(-0.70f, 0.05f, "Absence of physical sensation, cold core|Dorsal vagal protective shutdown|Gently rub hands together and breathe")),
            Triple("numb_apathetic", "Apathetic", Triple(-0.60f, 0.10f, "Listless shrug, indifferent voice|Conservation of depleted energy|Do not force excitement; allow resting")),
            Triple("numb_blunted", "Emotionally Blunted", Triple(-0.65f, 0.08f, "Muted affect, flat response to news|Protective damper over high pain|Eat something with strong sensory taste")),
            Triple("numb_dissociated", "Dissociated", Triple(-0.80f, 0.15f, "Floating above body, dreamlike view|Survival response to trauma overload|Press feet into ground, name 5 colors")),
            Triple("numb_flat_affect", "Flat Affect", Triple(-0.55f, 0.05f, "Immobile face, monotone voice|Nervous system in low-voltage mode|Listen to rhythmic heartbeat or drumming")),
            Triple("numb_indifferent", "Indifferent", Triple(-0.40f, 0.10f, "Neutral shrug, lack of care|Defense against repeated disappointment|Honor the boundary of not caring")),
            Triple("numb_listless", "Listless", Triple(-0.58f, 0.08f, "Heavy limbs, drifting attention|Depletion of motivational neurotransmitters|Rest without screen stimulation")),
            Triple("numb_unresponsive", "Unresponsive", Triple(-0.75f, 0.05f, "Blank stare, delayed processing|Shutdown under sensory deluge|Reduce lights, sound, and demands")),
            Triple("numb_detached", "Detached", Triple(-0.50f, 0.12f, "Analytical distance, no feeling|Observing life as an outsider|Notice physical temperature of palms")),
            Triple("numb_emotionless", "Emotionless", Triple(-0.60f, 0.05f, "Empty glass container sensation|Temporary quietus of feelings|Allow this resting interlude")),
            Triple("numb_burnout_paralysis", "Burnout Paralysis", Triple(-0.85f, 0.10f, "Inability to initiate tasks|Exhaustion of adrenal and neural reserves|Declare a mandatory rest day")),
            Triple("numb_anhedonic", "Anhedonic", Triple(-0.78f, 0.12f, "Gray food, joyless music, flat|Inability to register pleasure|Take a warm bath with epsom salts")),
            Triple("numb_hollow", "Hollow Numbness", Triple(-0.74f, 0.08f, "Scooped out chest, vacant stare|Feeling like an empty shell|Nourish body with warm comforting broth")),
            Triple("numb_frozen_internally", "Frozen Internally", Triple(-0.82f, 0.10f, "Ice encased heart, rigid stillness|Dorsal vagal freeze state|Apply a warm heating pad to belly")),
            Triple("numb_mechanized", "Mechanized", Triple(-0.55f, 0.20f, "Robotic movements, autopilot|Survival by going through the motions|Pause and take three conscious breaths")),
            Triple("numb_void", "The Void", Triple(-0.88f, 0.05f, "Total vacuum in consciousness|Profound emptiness of experience|Remember emptiness precedes new creation")),
            Triple("numb_desensitized", "Desensitized", Triple(-0.50f, 0.10f, "Toughened skin, lack of flinch|Habituation to chronic stress|Remove yourself from toxic noise")),
            Triple("numb_emotionally_muted", "Emotionally Muted", Triple(-0.52f, 0.08f, "Volume turned down to 10%|Systemic noise cancellation|Spend an hour in quiet nature")),
            Triple("numb_drained_zero", "Drained to Zero", Triple(-0.85f, 0.05f, "Battery blinking empty|Complete zeroing of bio-energy|Sleep and replenish electrolytes")),
            Triple("numb_catatonic_still", "Catatonic Stillness", Triple(-0.90f, 0.02f, "Immobility, staring at wall|Extreme defense against overload|Ensure safe resting environment")),
            Triple("numb_blase", "Blasé", Triple(-0.35f, 0.15f, "Weary cynicism, jaded smirk|Over-saturation of novelty|Simplify your life down to essentials")),
            Triple("numb_disengaged", "Disengaged", Triple(-0.45f, 0.12f, "Hands off steering wheel feeling|Checked out of the situation|Re-evaluate if you need to be there")),
            Triple("numb_unmoved", "Unmoved", Triple(-0.40f, 0.08f, "Solid stone wall, no ripple|Immunity to emotional appeal|Examine if protection has become a cage")),
            Triple("numb_passive_stasis", "Passive Stasis", Triple(-0.62f, 0.06f, "Drifting in current, no paddle|Loss of active will|Make one tiny choice today")),
            Triple("numb_somatic_freeze", "Somatic Freeze", Triple(-0.80f, 0.08f, "Cold extremities, shallow breath|Threat response freezing motor output|Shake out arms and jump gently")),
            Triple("numb_gray_fog", "Gray Numbness", Triple(-0.68f, 0.08f, "Colorless perception of room|Loss of emotional saturation|Step out into green foliage")),
            Triple("numb_disconnected_body", "Disconnected from Body", Triple(-0.76f, 0.10f, "Head disconnected from neck|Living entirely in abstract thought|Do barefoot grounding on soil/rug")),
            Triple("numb_chronic_burnout", "Chronic Burnout", Triple(-0.86f, 0.12f, "Bone-deep weariness, cynicism|Months of unsustainable exertion|Radically renegotiate commitments")),
            Triple("numb_deadened", "Deadened", Triple(-0.84f, 0.05f, "Lead weight in limbs, no spark|Deep suppression of all affect|Treat yourself as a patient in recovery")),
            Triple("numb_petrified", "Petrified Numbness", Triple(-0.80f, 0.08f, "Turned to stone feeling|Shock calcified into stiffness|Gentle stretching with warm oil")),
            Triple("numb_stagnant", "Stagnant", Triple(-0.55f, 0.10f, "Still pond without inflow|Lack of circulation of energy|Open windows, air out room, stretch")),
            Triple("numb_inert", "Inert", Triple(-0.65f, 0.05f, "Object at rest unable to move|Newtonian resistance to motion|Ask a friend to help you take step 1")),
            Triple("numb_exhausted_void", "Emotionally Exhausted", Triple(-0.82f, 0.08f, "Dry well, hollow bucket|Giving until nothing remains|Refill your own well with deep rest")),
            Triple("numb_blank", "Blank Slate", Triple(-0.40f, 0.05f, "Clear whiteboard, no marks|Post-storm neutral clearing|Rest in this clean slate")),
            Triple("numb_anesthetized", "Anesthetized Peace", Triple(0.10f, 0.08f, "Pain is blocked, floating calm|Temporary grace of feeling no pain|Use this relief to heal gently"))
        )
        for (item in numbData) {
            val parts = item.third.third.split("|")
            list.add(e(item.first, item.second, EmotionCategory.NUMBNESS_APATHY, item.third.first, item.third.second, parts[0], parts[1], parts[2]))
        }

        list
    }

    val totalCount: Int by lazy { allEmotions.size }

    fun findById(id: String): Emotion? = allEmotions.find { it.id == id }

    fun findByName(name: String): Emotion? = allEmotions.find { it.name.equals(name, ignoreCase = true) }

    fun getByCategory(category: EmotionCategory): List<Emotion> =
        allEmotions.filter { it.category == category }
}
